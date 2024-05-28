package org.alamnr.boottest.mockito_tips_and_tricks;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockitoTipsAndTricksApplication {

	// private final RandomQuoteClient randomQuoteClient;

	// public MockitoTipsAndTricksApplication(RandomQuoteClient randomQuoteClient){
	// 	this.randomQuoteClient = randomQuoteClient;
	// }

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(MockitoTipsAndTricksApplication.class, args);
		// TodoClient todoClient = new TodoClient();
		// List<Todo> todos = todoClient.findAll();
		// System.out.println(todos);
	}

	// @Bean
	// CommandLineRunner commandLineRunner(){
	// 	return args -> {
	// 		System.out.println(randomQuoteClient.getRandomQuoteUsingRestTemplate());
	// 		System.out.println(randomQuoteClient.getRandomQuoteUsingRestClient());
	// 	};
	//}


}
