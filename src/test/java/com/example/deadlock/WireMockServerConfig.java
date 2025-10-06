package com.example.deadlock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WireMockServerConfig {
	@Bean(initMethod = "start", destroyMethod = "stop")
	public WireMockServer wireMockServer() {
		WireMockConfiguration config = WireMockConfiguration.options()
				.port(8081);
		WireMockServer wireMockServer = new WireMockServer(config);
		wireMockServer.addStubMapping(StubMapping.buildFrom("""
				{
				  "request": {
				    "method": "GET",
				    "url": "/db-credentials"
				  },
				  "response": {
				    "status": 200,
				    "headers": {
				      "Content-Type": "application/json"
				    },
				    "body": "{\\"username\\":\\"testuser\\",\\"password\\":\\"testpass\\"}"
				  }
				}
				"""));
		return wireMockServer;
	}
}
