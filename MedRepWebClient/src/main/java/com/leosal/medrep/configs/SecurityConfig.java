//package com.leosal.medrep.configs;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
//import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
//
//@Configuration
//@EnableOAuth2Sso
//@EnableGlobalMethodSecurity(prePostEnabled=true)
//public class SecurityConfig extends GlobalMethodSecurityConfiguration {
//	
//	@Value("${security.oauth2.client.access-token-uri}")
//	private String accessTokenUri;
//	
//	@Value("${security.oauth2.client.client-id}")
//	private String clientId;
//	
//	@Value("${security.oauth2.client.client-secret}")
//	private String clientSecret;
//	
//	@Value("${medrep.oauth2.rest.username}")
//	private String restUser;
//	
//	@Value("${medrep.oauth2.rest.password}")
//	private String restPassword;
//	
//	
//	
//	@Override
//	protected MethodSecurityExpressionHandler createExpressionHandler() {
//		return new OAuth2MethodSecurityExpressionHandler();
//	}
//	
////	@LoadBalanced
////	@Bean
////	public RestTemplate serviceComunicationRestTemplate() {
////		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
////		
////		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
////		resourceDetails.setAccessTokenUri(accessTokenUri);
////		
////		resourceDetails.setClientId(clientId);
////		resourceDetails.setClientSecret(clientSecret);
////		
////		resourceDetails.setUsername(restUser);
////		resourceDetails.setPassword(restPassword);
////		
////		OAuth2RestTemplate template  = new OAuth2RestTemplate(resourceDetails);
////		
////		return template;
////	}
//
//}
