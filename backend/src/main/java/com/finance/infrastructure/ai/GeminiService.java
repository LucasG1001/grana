package com.finance.infrastructure.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;
import java.util.List;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=";
    
    // Note: Gemini API requires specific JSON structure.
    private static final String SYSTEM_PROMPT = 
        "Você é um extrator de dados financeiros. Analise o texto e retorne APENAS um JSON válido, sem markdown, sem explicação:\n" +
        "{\n" +
        "  \"valor\": <número positivo>,\n" +
        "  \"tipo\": \"<RECEITA ou DESPESA>\",\n" +
        "  \"categoria\": \"<uma de: Alimentação, Transporte, Moradia, Saúde, Lazer, Educação, Salário, Freelance, Outros>\",\n" +
        "  \"descricao\": \"<resumo curto>\",\n" +
        "  \"data\": \"<YYYY-MM-DD ou null se não mencionada>\"\n" +
        "}\n" +
        "Regra: se a data não for mencionada, retorne null (o backend assumirá a data atual). Sempre retorne um JSON sintaticamente válido.";

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
                        Map.of("text", SYSTEM_PROMPT + "\n\nTexto do usuário: " + text)
                    ))
                )
            ));

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            String response = restTemplate.postForObject(GEMINI_API_URL + apiKey, entity, String.class);
            
            JsonNode rootNode = objectMapper.readTree(response);
            String extractedJson = rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();
            
            // Remove markdown code blocks if gemini returns them despite prompt
            extractedJson = extractedJson.replaceAll("```json", "").replaceAll("```", "").trim();
            
            return extractedJson;
        } catch (Exception e) {
            throw new RuntimeException("Error communicating with Gemini API or parsing response: " + e.getMessage(), e);
        }
    }
}
