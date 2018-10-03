package com.leosal.medrep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
public class MedRepUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedRepUserServiceApplication.class, args);
	}
	
	@Autowired
	private ResourceServerProperties sso;
	
	@Bean
	public ResourceServerTokenServices userInfoTokenServices() {
		return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
	}
	
	@RequestMapping("/current")
	@PreAuthorize("#oauth2.hasScope('data_read') and hasAuthority('ROLE_ADMIN')")
	public String getCurrentUser() {
		return "This is current user";
	}
}
