package com.leosal.medrep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient
@EnableOAuth2Sso
@EnableCircuitBreaker
@ComponentScan(basePackages= {"com.leosal.medrep.configs", "com.leosal.medrep.services", "com.leosal.medrep.rest"})
public class MedRepClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedRepClientServiceApplication.class, args);
	}
}
