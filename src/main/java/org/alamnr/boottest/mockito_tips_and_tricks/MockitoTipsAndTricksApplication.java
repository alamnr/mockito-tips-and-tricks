package org.alamnr.boottest.mockito_tips_and_tricks;

import java.io.IOException;

import org.alamnr.boottest.mockito_tips_and_tricks.dan_vega.restpojo.JsonPlaceholderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class MockitoTipsAndTricksApplication {

	// private final RandomQuoteClient randomQuoteClient;

	// public MockitoTipsAndTricksApplication(RandomQuoteClient randomQuoteClient){
	// this.randomQuoteClient = randomQuoteClient;
	// }

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(MockitoTipsAndTricksApplication.class, args);
		// TodoClient todoClient = new TodoClient();
		// List<Todo> todos = todoClient.findAll();
		// System.out.println(todos);
	}

	@Bean
	JsonPlaceholderService jsonPlaceholderService() {
		RestClient client = RestClient.create("https://jsonplaceholder.typicode.com");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory
				.builderFor(RestClientAdapter.create(client))
				.build();
		return factory.createClient(JsonPlaceholderService.class);
	}

	// @Bean
	// CommandLineRunner commandLineRunner(){
	// return args -> {
	// System.out.println(randomQuoteClient.getRandomQuoteUsingRestTemplate());
	// System.out.println(randomQuoteClient.getRandomQuoteUsingRestClient());
	// };
	// }

}
