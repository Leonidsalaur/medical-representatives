package com.leosal.medrep;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
public class MedrepConfigclientTestApplication {
	
	@Value("${greeting}")
	private String greeting;
	
	public static void main(String[] args) {
		SpringApplication.run(MedrepConfigclientTestApplication.class, args);
	}
	
	@RequestMapping("/greeting")
	public String showGreeting() {
		return greeting;
	}
}
