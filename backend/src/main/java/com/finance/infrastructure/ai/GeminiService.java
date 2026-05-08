package com.finance.infrastructure.ai;

import com.finance.domain.model.TransactionCategories;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent?key=";

    // Note: Gemini API requires specific JSON structure.
    private static final String SYSTEM_PROMPT = """
            Você é um extrator de dados financeiros. Analise o texto e retorne APENAS um JSON válido, sem markdown, sem explicação:
            {
              "valor": <número positivo>,
              "tipo": "<RECEITA ou DESPESA>",
              "categoria": "<uma das categorias fixas>",
              "descricao": "<resumo curto>",
              "data": "<YYYY-MM-DD ou null se não mencionada>",
              "formaPagamento": "<DINHEIRO, DEBITO, CREDITO, PIX, TRANSFERENCIA ou OUTRO>",
              "totalParcelas": <número inteiro, use 1 se não for parcelado>
            }
            Categorias fixas permitidas: %s.
            Regras:
            - Nunca invente uma categoria fora da lista fixa.
            - Se o texto mencionar cartão de crédito, crédito ou compra parcelada, use formaPagamento CREDITO.
            - Para compra parcelada, valor deve ser o valor total da compra. Se o texto disser "12x de 100", valor deve ser 1200 e totalParcelas deve ser 12.
            - Se a data não for mencionada, retorne null (o backend assumirá a data atual).
            - Sempre retorne um JSON sintaticamente válido.
            """.formatted(TransactionCategories.promptOptions());

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GeminiService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public String analyzeTransactionText(String text) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String requestBody = objectMapper.writeValueAsString(Map.of(
                "contents", List.of(
                    Map.of("parts", List.of(
                        Map.of("text", SYSTEM_PROMPT + "\n\nTexto do usuário: " + text))))));

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            java.net.URI uri = java.net.URI.create(GEMINI_API_URL + apiKey);
            String response = restTemplate.postForObject(uri, entity, String.class);
            JsonNode rootNode = objectMapper.readTree(response);
            String extractedJson = rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text")
                .asText();

            // Remove markdown code blocks if Gemini returns them despite the prompt.
            extractedJson = extractedJson.replaceAll("```json", "").replaceAll("```", "").trim();

            return extractedJson;
        } catch (Exception e) {
            throw new RuntimeException("Error communicating with Gemini API or parsing response: " + e.getMessage(), e);
        }
    }
}
