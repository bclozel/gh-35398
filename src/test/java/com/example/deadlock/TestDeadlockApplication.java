package com.example.deadlock;

import org.springframework.boot.SpringApplication;

public class TestDeadlockApplication {

	public static void main(String[] args) {
		SpringApplication.from(DeadlockApplication::main).with(WireMockServerConfig.class).run(args);
	}

}
