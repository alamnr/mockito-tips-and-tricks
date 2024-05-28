package org.alamnr.boottest.mockito_tips_and_tricks.resttemplate;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class RandomQuoteClient {
    private final RestTemplate restTemplate;
    private final String API_KEY = "?api_key=d5R6pzbMPgChOTJTIHJUYStcdsfTnMKJWn7LG0b6";
    private final RestClient restClient;

    public RandomQuoteClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder
                            .rootUri("https://quotes.rest")
                            .setConnectTimeout(Duration.ofSeconds(2))
                            .setReadTimeout(Duration.ofSeconds(2))
                            .build();
        this.restClient = RestClient.create("https://quotes.rest");
    }

    public String getRandomQuoteUsingRestTemplate() {
        return this.restTemplate.getForObject("/qod"+API_KEY, JsonNode.class)
                    .get("contents")
                    .get("quotes").get(0)
                    .get("quote").asText();
    }

    public String getRandomQuoteUsingRestClient(){
        return this.restClient
                   .get()
                   .uri("/qod"+API_KEY)
                   .retrieve().body(String.class);
    }


}
