package com.leosal.medrep;

import java.util.Arrays;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

public class DesktopTest {

	public static void main(String[] args) {
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		
		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
		resourceDetails.setAccessTokenUri("http://localhost:9020/authorize/oauth/token");
		
		resourceDetails.setScope(Arrays.asList("data_read"));
		resourceDetails.setClientId("medrepapp");
		resourceDetails.setClientSecret("medrepappsecret");
		
		resourceDetails.setUsername("leonid");
		resourceDetails.setPassword("pass1");
		
		OAuth2RestTemplate template  = new OAuth2RestTemplate(resourceDetails);
		
		String token = template.getAccessToken().toString();
		
		System.out.println("Token: " + token);
		
		String s = template.getForObject("http://localhost:9010/tests/greeting", String.class);
		
		System.out.println("Response: " + s);

	}

}
