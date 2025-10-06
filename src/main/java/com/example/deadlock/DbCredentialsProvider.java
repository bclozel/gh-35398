package com.example.deadlock;

import reactor.core.scheduler.Schedulers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DbCredentialsProvider {

	private final WebClient webClient;

	public DbCredentialsProvider(@Value("${service.base-url:http://localhost:8081}") String url, WebClient.Builder builder) {
		this.webClient = builder.baseUrl(url).build();
	}

	public Creds getCredentials() {
		return webClient.get().uri("/db-credentials")
				.retrieve()
				.bodyToMono(Creds.class)
				.subscribeOn(Schedulers.immediate())
				.block();
	}

	public record Creds(String username, String password) { }


}
