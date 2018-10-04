package com.leosal.medrep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableEurekaClient
@EnableOAuth2Sso
public class MedRepUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedRepUserServiceApplication.class, args);
	}
	
	@Bean
	public ResourceServerTokenServices userInfoTokenServices() {
		return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
	}
	
	@Autowired
	private ResourceServerProperties sso;
	
	@Autowired
	private RestTemplate webTemplate;
	
	
	
	@RequestMapping("/current")
	@PreAuthorize("#oauth2.hasScope('data_read') and hasAuthority('ROLE_ADMIN')")
	public String getCurrentUser() {
		
		ResponseEntity<String> greeting = webTemplate.getForEntity("http://localhost:9010/tests/greeting", String.class);
		
		return "This is current user: " + greeting.getBody();
	}
}
