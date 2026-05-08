package com.finance.web.controller;

import com.finance.infrastructure.ai.GeminiService;
import com.finance.infrastructure.persistence.SpringDataTransactionRepository;
import com.finance.web.dto.CreateTransactionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SpringDataTransactionRepository transactionRepository;

    @MockitoBean
    private GeminiService geminiService;

    @BeforeEach
    void cleanDatabase() {
        transactionRepository.deleteAll();
    }

    @Test
    public void testCreateFromNaturalLanguage() throws Exception {
        String mockGeminiResponse = "{\n" +
            "  \"valor\": 150.00,\n" +
            "  \"tipo\": \"DESPESA\",\n" +
            "  \"categoria\": \"Restaurante\",\n" +
            "  \"descricao\": \"Jantar no restaurante\",\n" +
            "  \"data\": \"2023-10-25\",\n" +
            "  \"formaPagamento\": \"DEBITO\",\n" +
            "  \"totalParcelas\": 1\n" +
            "}";

        when(geminiService.analyzeTransactionText(anyString())).thenReturn(mockGeminiResponse);

        CreateTransactionRequest request = new CreateTransactionRequest("Gastei 150 com jantar");

        mockMvc.perform(post("/api/transactions/natural")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.valor").value(150.0))
            .andExpect(jsonPath("$.categoria").value("Alimentação"))
            .andExpect(jsonPath("$.tipo").value("DESPESA"))
            .andExpect(jsonPath("$.formaPagamento").value("DEBITO"))
            .andExpect(jsonPath("$.totalParcelas").value(1));
    }

    @Test
    public void testCreateCreditInstallmentsFromNaturalLanguage() throws Exception {
        String mockGeminiResponse = "{\n" +
            "  \"valor\": 1200.00,\n" +
            "  \"tipo\": \"DESPESA\",\n" +
            "  \"categoria\": \"Compras\",\n" +
            "  \"descricao\": \"Celular\",\n" +
            "  \"data\": \"2026-05-08\",\n" +
            "  \"formaPagamento\": \"CREDITO\",\n" +
            "  \"totalParcelas\": 3\n" +
            "}";

        when(geminiService.analyzeTransactionText(anyString())).thenReturn(mockGeminiResponse);

        CreateTransactionRequest request = new CreateTransactionRequest("Comprei um celular de 1200 no crédito em 3x");

        mockMvc.perform(post("/api/transactions/natural")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.valor").value(400.0))
            .andExpect(jsonPath("$.formaPagamento").value("CREDITO"))
            .andExpect(jsonPath("$.parcelaAtual").value(1))
            .andExpect(jsonPath("$.totalParcelas").value(3));

        mockMvc.perform(get("/api/transactions"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    public void testGetSummaryEmpty() throws Exception {
        mockMvc.perform(get("/api/transactions/summary"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.totalReceitas").value(0))
            .andExpect(jsonPath("$.totalDespesas").value(0));
    }
}
