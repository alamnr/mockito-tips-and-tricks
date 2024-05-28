package org.alamnr.boottest.mockito_tips_and_tricks.dan_vega.client;

import java.util.List;

import org.alamnr.boottest.mockito_tips_and_tricks.dan_vega.restpojo.Post;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PostClient {
    
    private final RestClient restClient;

    public PostClient(RestClient.Builder builder){
       // JdkClientHttpRequestFactory  requestFactory = new JdkClientHttpRequestFactory();
        this.restClient = builder
                          .baseUrl("https://jsonplaceholder.typicode.com")  
                          //.requestFactory(requestFactory)
                          .build();
    }

    public List<Post> findAll(){
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }


}
