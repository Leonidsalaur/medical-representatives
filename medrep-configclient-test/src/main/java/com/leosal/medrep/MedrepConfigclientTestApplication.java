package com.leosal.medrep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableEurekaClient
public class MedrepConfigclientTestApplication {
	@Value("${security.oauth2.client.access-token-uri}")
	private String accessTokenUri;
	
	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;
	
	@Value("${medrep.oauth2.rest.username}")
	private String restUser;
	
	@Value("${medrep.oauth2.rest.password}")
	private String restPassword;
	
	@Value("${greeting}")
	private String greeting;
	
	public static void main(String[] args) {
		SpringApplication.run(MedrepConfigclientTestApplication.class, args);
	}
	
	@Autowired
	private ResourceServerProperties sso;
	
	@Bean
	public ResourceServerTokenServices userInfoTokenServices() {
		return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
	}
	
	@Bean
	public RestTemplate serviceComunicationRestTemplate() {
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		
		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
		resourceDetails.setAccessTokenUri(accessTokenUri);
		
		resourceDetails.setClientId(clientId);
		resourceDetails.setClientSecret(clientSecret);
		
		resourceDetails.setUsername(restUser);
		resourceDetails.setPassword(restPassword);
		
		OAuth2RestTemplate template  = new OAuth2RestTemplate(resourceDetails);
		
		return template;
	}
	
	@RequestMapping("/greeting")
	public String showGreeting() {
		//System.out.println("TestService/greeting....");
		return greeting;
	}
}
