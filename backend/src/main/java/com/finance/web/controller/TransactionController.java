package com.finance.web.controller;

import com.finance.application.usecase.*;
import com.finance.domain.model.Transaction;
import com.finance.domain.model.TransactionType;
import com.finance.infrastructure.ai.GeminiService;
import com.finance.web.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final CreateTransactionUseCase createTransactionUseCase;
    private final ListTransactionsUseCase listTransactionsUseCase;
    private final UpdateTransactionUseCase updateTransactionUseCase;
    private final DeleteTransactionUseCase deleteTransactionUseCase;
    private final GeminiService geminiService;
    private final ObjectMapper objectMapper;

    public TransactionController(CreateTransactionUseCase createTransactionUseCase,
                                 ListTransactionsUseCase listTransactionsUseCase,
                                 UpdateTransactionUseCase updateTransactionUseCase,
                                 DeleteTransactionUseCase deleteTransactionUseCase,
                                 GeminiService geminiService,
                                 ObjectMapper objectMapper) {
        this.createTransactionUseCase = createTransactionUseCase;
        this.listTransactionsUseCase = listTransactionsUseCase;
        this.updateTransactionUseCase = updateTransactionUseCase;
        this.deleteTransactionUseCase = deleteTransactionUseCase;
        this.geminiService = geminiService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/natural")
    public ResponseEntity<TransactionResponse> createFromNaturalLanguage(@RequestBody CreateTransactionRequest request) {
        try {
            String jsonResult = geminiService.analyzeTransactionText(request.textoLivre());
            JsonTransaction tempTx = objectMapper.readValue(jsonResult, JsonTransaction.class);
            
            LocalDate data = tempTx.data() != null ? tempTx.data() : LocalDate.now();
            Transaction transaction = new Transaction(
                null,
                tempTx.valor(),
                tempTx.categoria(),
                tempTx.descricao(),
                data,
                TransactionType.valueOf(tempTx.tipo().toUpperCase())
            );
            
            Transaction saved = createTransactionUseCase.execute(transaction);
            return ResponseEntity.ok(toResponse(saved));
        } catch (Exception e) {
            System.err.println("Erro no endpoint natural: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createManually(@RequestBody UpdateTransactionRequest request) {
        Transaction transaction = new Transaction(
            null,
            request.valor(),
            request.categoria(),
            request.descricao(),
            request.data() != null ? request.data() : LocalDate.now(),
            TransactionType.valueOf(request.tipo().toUpperCase())
        );
        Transaction saved = createTransactionUseCase.execute(transaction);
        return ResponseEntity.ok(toResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> listTransactions(@RequestParam(required = false) String mes) {
        List<Transaction> transactions;
        if (mes != null && !mes.isEmpty()) {
            YearMonth ym = YearMonth.parse(mes); // format: YYYY-MM
            transactions = listTransactionsUseCase.executeByMonth(ym.getMonthValue(), ym.getYear());
        } else {
            transactions = listTransactionsUseCase.execute();
        }
        return ResponseEntity.ok(transactions.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable Long id, @RequestBody UpdateTransactionRequest request) {
        Transaction input = new Transaction(
            id,
            request.valor(),
            request.categoria(),
            request.descricao(),
            request.data(),
            TransactionType.valueOf(request.tipo().toUpperCase())
        );
        Transaction updated = updateTransactionUseCase.execute(id, input);
        return ResponseEntity.ok(toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        deleteTransactionUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary")
    public ResponseEntity<SummaryResponse> getSummary() {
        List<Transaction> transactions = listTransactionsUseCase.execute();
        
        BigDecimal receitas = transactions.stream()
            .filter(t -> t.getTipo() == TransactionType.RECEITA)
            .map(Transaction::getValor)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
            
        BigDecimal despesas = transactions.stream()
            .filter(t -> t.getTipo() == TransactionType.DESPESA)
            .map(Transaction::getValor)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
            
        BigDecimal saldo = receitas.subtract(despesas);
        
        Map<String, BigDecimal> porCategoria = transactions.stream()
            .filter(t -> t.getTipo() == TransactionType.DESPESA)
            .collect(Collectors.groupingBy(
                Transaction::getCategoria,
                Collectors.reducing(BigDecimal.ZERO, Transaction::getValor, BigDecimal::add)
            ));
            
        return ResponseEntity.ok(new SummaryResponse(receitas, despesas, saldo, porCategoria));
    }

    private TransactionResponse toResponse(Transaction t) {
        return new TransactionResponse(t.getId(), t.getValor(), t.getCategoria(), t.getDescricao(), t.getData(), t.getTipo().name());
    }

    // Helper record for parsing Gemini output
    private record JsonTransaction(BigDecimal valor, String tipo, String categoria, String descricao, LocalDate data) {}
}
