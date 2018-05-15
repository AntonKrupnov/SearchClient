package com.github.yell13.SearchClient;

import com.github.yell13.SearchClient.client.Client;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import javax.annotation.PostConstruct;

@EnableFeignClients
@SpringBootApplication
public class SearchClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchClientApplication.class, args);
	}

	@PostConstruct
	public void doIt() {
		Client client = Feign.builder().contract(new SpringMvcContract())
				.target(Client.class, "http://localhost:8080");

		client.storeDocument("hi", "Hi Tom");
		client.storeDocument("hello", "Hello Tom");

		System.out.println("GET document by key 'hi': " + client.getDocument("hi"));
		System.out.println("Search documents with word 'Tom': " + client.findKeysWithTokens("Tom"));
		System.out.println("Search documents with word 'Hi': " + client.findKeysWithTokens("Hi"));
		System.out.println("Search documents with words 'Hi' and 'Hello': " + client.findKeysWithTokens("Hi Hello"));
	}
}
