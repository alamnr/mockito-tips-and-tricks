package org.alamnr.boottest.mockito_tips_and_tricks.dan_vega.todo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TodoClient {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com/todos";
    private final HttpClient client;

    private final ObjectMapper objectMapper;

    public TodoClient() {
        client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<Todo> findAll() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), new TypeReference<>() {
        });
    }

    public Todo findById(int i) throws IOException, InterruptedException, TodoNotFoundException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + i))
                .GET()
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 404) {
            throw new TodoNotFoundException("Todo not Found");
        }
        return objectMapper.readValue(response.body(), Todo.class);
    }

    public HttpResponse<String> create(Todo todo) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(todo)))
                .build();
        return client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> update(Todo todo) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + todo.id()))
                .PUT(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(todo)))
                .build();

        return client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> delete(Todo todo) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + todo.id()))
                .DELETE()
                .build();

        return client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
