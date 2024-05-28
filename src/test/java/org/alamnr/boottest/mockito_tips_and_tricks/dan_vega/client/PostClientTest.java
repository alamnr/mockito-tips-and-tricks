/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package org.alamnr.boottest.mockito_tips_and_tricks.dan_vega.client;

import java.util.List;

import org.alamnr.boottest.mockito_tips_and_tricks.dan_vega.restpojo.Post;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
/**
 *
 * @author Alam
 */
@RestClientTest(PostClient.class)
public class PostClientTest {


    @Autowired
    MockRestServiceServer mockRestServiceServer;

    @Autowired
    PostClient postClient;
    
    @Autowired
    ObjectMapper objectMapper;
    /**
     * Test of findAll method, of class PostClient.
     * @throws JsonProcessingException 
     */
    @Test
    void shouldFindAllPost() throws JsonProcessingException {
        // given
        List<Post> data = List.of(
                        new Post(1,1,"Hello World!",  "This is my first post"),
                        new Post( 2, 2, "Test RestClient with @RestClientTest","This is my second post"));
        // when

        mockRestServiceServer.expect(requestTo("https://jsonplaceholder.typicode.com/posts"))
                             .andRespond(withSuccess(objectMapper.writeValueAsString(data), MediaType.APPLICATION_JSON));
        // then

        List<Post> posts = postClient.findAll();
        assertEquals(2, posts.size());
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    
}