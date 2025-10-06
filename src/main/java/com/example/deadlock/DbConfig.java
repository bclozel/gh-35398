package com.example.deadlock;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
@DependsOn("wireMockServer") // need to run TestDeadlockApplication
public class DbConfig {

	@Bean
	public DataSource dataSource(DbCredentialsProvider credentialsProvider) {
		// Uses credentials from provider (which triggers WebClient call)
		DbCredentialsProvider.Creds credentials = credentialsProvider.getCredentials();
		return DataSourceBuilder.create()
				.url("jdbc:h2:mem:testdb")
				.username(credentials.username())
				.password(credentials.password())
				.build();
	}

}
