package org.alamnr.boottest.mockito_tips_and_tricks.deepstubs;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;



public class InspirationalQuoteClient {

    private final WebClient webclient;

    public InspirationalQuoteClient(WebClient webclient) {
        this.webclient = webclient;
    }

    public String fetchRandomQuote(){
        try {
                return this.webclient
                            .get()
                            .uri("/api/quotes")
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();

        } catch(WebClientException webClientException){
            return "Every time a mock returns a mock, a fairy dies";
        }
    }
    
}
