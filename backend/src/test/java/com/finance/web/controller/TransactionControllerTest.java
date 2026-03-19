package com.finance.web.controller;

import com.finance.web.dto.CreateTransactionRequest;
import com.finance.infrastructure.ai.GeminiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private GeminiService geminiService;

    @Test
    public void testCreateFromNaturalLanguage() throws Exception {
        String mockGeminiResponse = "{\n" +
                "  \"valor\": 150.00,\n" +
                "  \"tipo\": \"DESPESA\",\n" +
                "  \"categoria\": \"Alimentação\",\n" +
                "  \"descricao\": \"Jantar no restaurante\",\n" +
                "  \"data\": \"2023-10-25\"\n" +
                "}";

        when(geminiService.analyzeTransactionText(anyString())).thenReturn(mockGeminiResponse);

        CreateTransactionRequest request = new CreateTransactionRequest("Gastei 150 com jantar");

        mockMvc.perform(post("/api/transactions/natural")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valor").value(150.0))
                .andExpect(jsonPath("$.categoria").value("Alimentação"))
                .andExpect(jsonPath("$.tipo").value("DESPESA"));
    }

    @Test
    public void testGetSummaryEmpty() throws Exception {
        mockMvc.perform(get("/api/transactions/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalReceitas").value(0))
                .andExpect(jsonPath("$.totalDespesas").value(0));
    }
}
