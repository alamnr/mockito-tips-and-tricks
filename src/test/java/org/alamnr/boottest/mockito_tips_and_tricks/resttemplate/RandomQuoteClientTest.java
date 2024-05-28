/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package org.alamnr.boottest.mockito_tips_and_tricks.resttemplate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

/**
 *
 * @author Alam
 */
@RestClientTest(RandomQuoteClient.class)
public class RandomQuoteClientTest {

    private final String API_KEY = "?api_key=d5R6pzbMPgChOTJTIHJUYStcdsfTnMKJWn7LG0b6";
    @Autowired
    private RandomQuoteClient randomQuoteClient;
    @Autowired
    private MockRestServiceServer mockRestServiceServer;

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

    @Test
    void testContext() {
        assertNotNull(mockRestServiceServer);
        assertNotNull(randomQuoteClient);
    }

    /**
     * Test of getRandomQuoteUsingRestTemplate method, of class RandomQuoteClient.
     */
    @Test
    void testGetRandomQuoteUsingRestTemplate() {

        String response = """
                {
                    
                    "contents":   {
                        "quotes" : [
                            {
                                "author" : "duke",
                                "quote" : "Lorel Ipsum"
                            }
                        ]
                    }
                }
                """;

        this.mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo("/qod"+API_KEY))
                .andRespond(MockRestResponseCreators.withSuccess(response, MediaType.APPLICATION_JSON));
        String result = randomQuoteClient.getRandomQuoteUsingRestTemplate();
        assertEquals("Lorel Ipsum", result);
    }

    @Test
    void shouldFailOfTooManyRequestFromRemoteSystem(){
        this.mockRestServiceServer
            .expect(MockRestRequestMatchers.requestTo("/qod"+API_KEY))
            .andRespond(MockRestResponseCreators.withServerError());
        
        assertThrows(RuntimeException.class, () -> randomQuoteClient.getRandomQuoteUsingRestTemplate());
    }

    /**
     * Test of getRandomQuoteUsingRestClient method, of class RandomQuoteClient.
     */
    @Test
    void testGetRandomQuoteUsingRestClient() {

    }

}