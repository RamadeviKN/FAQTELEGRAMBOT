package com.faqtelegrambot.questionservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NlpService {

    @Value("${flask.api.url}") // This is where your Flask API is running
    private String flaskApiUrl;

    private final RestTemplate restTemplate;

    public NlpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getBestAnswer(String userQuery, List<Map<String, String>> questions) {
        // Prepare the request body as JSON
         // Create request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user_query", userQuery);
        requestBody.put("questions", questions);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            String answer;
            System.out.println("Sending request to Flask API...");
            // Send request to Flask API
            ResponseEntity<Map> response = restTemplate.exchange(
                    flaskApiUrl , HttpMethod.POST, entity, Map.class
            );

            // Extract answer from response
            if (response != null && ((String)response.getBody().get("answer")) == null) {
                answer = "ERROR";
            } else {
                answer = (String) response.getBody().get("answer");
            }
            System.out.println("Received answer from Flask API : " + answer);
            return answer;

        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, something went wrong.";
        }
    }
}
