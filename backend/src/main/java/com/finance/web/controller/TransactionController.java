package com.finance.web.controller;

import com.finance.application.usecase.CreateTransactionUseCase;
import com.finance.application.usecase.DeleteTransactionUseCase;
import com.finance.application.usecase.ListTransactionsUseCase;
import com.finance.application.usecase.UpdateTransactionUseCase;
import com.finance.domain.model.PaymentMethod;
import com.finance.domain.model.Transaction;
import com.finance.domain.model.TransactionCategories;
import com.finance.domain.model.TransactionType;
import com.finance.infrastructure.ai.GeminiService;
import com.finance.web.dto.SummaryResponse;
import com.finance.web.dto.TransactionResponse;
import com.finance.web.dto.UpdateTransactionRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private static final int MAX_INSTALLMENTS = 120;
    private static final Pattern INSTALLMENT_BY_X_PATTERN = Pattern.compile("(\\d{1,3})\\s*x");
    private static final Pattern INSTALLMENT_BY_WORD_PATTERN = Pattern.compile("(\\d{1,3})\\s*(parcelas|vezes)");

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
    public ResponseEntity<TransactionResponse> createFromNaturalLanguage(@RequestBody String rawBody) {
        try {
            JsonNode node = objectMapper.readTree(rawBody);
            if (node.isArray() && node.size() > 0) {
                node = node.get(0);
            }

            String textoLivre = node.path("textoLivre").asText();
            if (textoLivre == null || textoLivre.isEmpty()) {
                textoLivre = rawBody;
            }

            String jsonResult = geminiService.analyzeTransactionText(textoLivre);
            JsonTransaction tempTx = objectMapper.readValue(jsonResult, JsonTransaction.class);

            Transaction transaction = buildTransaction(
                tempTx.valor(),
                tempTx.categoria(),
                tempTx.descricao(),
                tempTx.data(),
                tempTx.tipo(),
                tempTx.formaPagamento(),
                tempTx.totalParcelas(),
                textoLivre
            );

            List<Transaction> saved = createTransactionUseCase.executeAll(expandInstallments(transaction));
            return ResponseEntity.ok(toResponse(saved.get(0)));
        } catch (Exception e) {
            System.err.println("Erro no endpoint natural: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createManually(@RequestBody UpdateTransactionRequest request) {
        Transaction transaction = buildTransaction(
            request.valor(),
            request.categoria(),
            request.descricao(),
            request.data(),
            request.tipo(),
            request.formaPagamento(),
            request.totalParcelas(),
            null
        );

        List<Transaction> saved = createTransactionUseCase.executeAll(expandInstallments(transaction));
        return ResponseEntity.ok(toResponse(saved.get(0)));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> listCategories() {
        return ResponseEntity.ok(TransactionCategories.ALL);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> listTransactions(@RequestParam(required = false) String mes) {
        List<Transaction> transactions;
        if (mes != null && !mes.isEmpty()) {
            YearMonth ym = YearMonth.parse(mes);
            transactions = listTransactionsUseCase.executeByMonth(ym.getMonthValue(), ym.getYear());
        } else {
            transactions = listTransactionsUseCase.execute();
        }
        return ResponseEntity.ok(transactions.stream().map(this::toResponse).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable Long id, @RequestBody UpdateTransactionRequest request) {
        Transaction input = buildTransaction(
            request.valor(),
            request.categoria(),
            request.descricao(),
            request.data(),
            request.tipo(),
            request.formaPagamento(),
            request.totalParcelas(),
            null
        );
        input.setId(id);

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

    private Transaction buildTransaction(BigDecimal valor, String categoria, String descricao, LocalDate data,
                                         String tipo, String formaPagamento, Integer totalParcelas,
                                         String textoLivre) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor da transação é obrigatório");
        }

        TransactionType transactionType = parseTransactionType(tipo);
        int normalizedInstallments = parseInstallments(totalParcelas, textoLivre);
        PaymentMethod paymentMethod = parsePaymentMethod(formaPagamento, textoLivre);
        if (normalizedInstallments > 1) {
            paymentMethod = PaymentMethod.CREDITO;
        }

        return new Transaction(
            null,
            valor,
            TransactionCategories.normalize(categoria),
            descricao,
            data != null ? data : LocalDate.now(),
            transactionType,
            paymentMethod,
            null,
            normalizedInstallments,
            null
        );
    }

    private List<Transaction> expandInstallments(Transaction transaction) {
        int totalInstallments = transaction.getTotalParcelas() != null ? transaction.getTotalParcelas() : 1;
        boolean isCreditInstallment = transaction.getTipo() == TransactionType.DESPESA
            && transaction.getFormaPagamento() == PaymentMethod.CREDITO
            && totalInstallments > 1;

        if (!isCreditInstallment) {
            transaction.setParcelaAtual(null);
            transaction.setTotalParcelas(1);
            transaction.setGrupoParcelamento(null);
            return List.of(transaction);
        }

        String installmentGroup = UUID.randomUUID().toString();
        long totalCents = transaction.getValor()
            .multiply(BigDecimal.valueOf(100))
            .setScale(0, RoundingMode.HALF_UP)
            .longValue();
        long baseCents = totalCents / totalInstallments;
        long remainderCents = totalCents % totalInstallments;

        List<Transaction> installments = new ArrayList<>();
        String baseDescription = transaction.getDescricao() != null && !transaction.getDescricao().isBlank()
            ? transaction.getDescricao()
            : transaction.getCategoria();

        for (int i = 1; i <= totalInstallments; i++) {
            long installmentCents = baseCents + (i <= remainderCents ? 1 : 0);
            BigDecimal installmentValue = BigDecimal.valueOf(installmentCents, 2);

            installments.add(new Transaction(
                null,
                installmentValue,
                transaction.getCategoria(),
                "%s (%d/%d)".formatted(baseDescription, i, totalInstallments),
                transaction.getData().plusMonths(i - 1),
                transaction.getTipo(),
                PaymentMethod.CREDITO,
                i,
                totalInstallments,
                installmentGroup
            ));
        }

        return installments;
    }

    private TransactionType parseTransactionType(String rawType) {
        if (rawType == null || rawType.isBlank()) {
            return TransactionType.DESPESA;
        }

        return TransactionType.valueOf(rawType.trim().toUpperCase(Locale.ROOT));
    }

    private PaymentMethod parsePaymentMethod(String rawPaymentMethod, String sourceText) {
        if (rawPaymentMethod != null && !rawPaymentMethod.isBlank()) {
            try {
                return PaymentMethod.valueOf(normalizeEnumToken(rawPaymentMethod));
            } catch (IllegalArgumentException ignored) {
                // Natural language fallback below.
            }
        }

        String normalizedText = normalizeText(sourceText);
        if (normalizedText.contains("debito")) {
            return PaymentMethod.DEBITO;
        }
        if (normalizedText.contains("credito") || normalizedText.contains("cartao") || normalizedText.contains("parcel")) {
            return PaymentMethod.CREDITO;
        }
        if (normalizedText.contains("pix")) {
            return PaymentMethod.PIX;
        }
        if (normalizedText.contains("dinheiro")) {
            return PaymentMethod.DINHEIRO;
        }
        if (normalizedText.contains("transferencia")) {
            return PaymentMethod.TRANSFERENCIA;
        }

        return PaymentMethod.OUTRO;
    }

    private int parseInstallments(Integer rawInstallments, String sourceText) {
        int inferredInstallments = inferInstallments(sourceText);
        int installments = rawInstallments != null && rawInstallments > 0
            ? Math.max(rawInstallments, inferredInstallments)
            : inferredInstallments;
        return Math.max(1, Math.min(installments, MAX_INSTALLMENTS));
    }

    private int inferInstallments(String sourceText) {
        String normalizedText = normalizeText(sourceText);
        Matcher byX = INSTALLMENT_BY_X_PATTERN.matcher(normalizedText);
        if (byX.find()) {
            return Integer.parseInt(byX.group(1));
        }

        Matcher byWord = INSTALLMENT_BY_WORD_PATTERN.matcher(normalizedText);
        if (byWord.find()) {
            return Integer.parseInt(byWord.group(1));
        }

        return 1;
    }

    private String normalizeEnumToken(String value) {
        return normalizeText(value).replace(" ", "_").toUpperCase(Locale.ROOT);
    }

    private String normalizeText(String value) {
        if (value == null) {
            return "";
        }

        return Normalizer.normalize(value, Normalizer.Form.NFD)
            .replaceAll("\\p{M}", "")
            .toLowerCase(Locale.ROOT)
            .replaceAll("[^a-z0-9]+", " ")
            .trim();
    }

    private TransactionResponse toResponse(Transaction t) {
        PaymentMethod paymentMethod = t.getFormaPagamento() != null ? t.getFormaPagamento() : PaymentMethod.OUTRO;
        Integer totalInstallments = t.getTotalParcelas() != null ? t.getTotalParcelas() : 1;

        return new TransactionResponse(
            t.getId(),
            t.getValor(),
            t.getCategoria(),
            t.getDescricao(),
            t.getData(),
            t.getTipo().name(),
            paymentMethod.name(),
            t.getParcelaAtual(),
            totalInstallments,
            t.getGrupoParcelamento()
        );
    }

    private record JsonTransaction(
        BigDecimal valor,
        String tipo,
        String categoria,
        String descricao,
        LocalDate data,
        String formaPagamento,
        Integer totalParcelas
    ) {}
}
