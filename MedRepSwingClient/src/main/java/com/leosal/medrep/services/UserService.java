package com.leosal.medrep.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
public class UserService {
	
	@Value("${medrep.login.ping.url}")
	private String loginPingURL;
	
	
	@Autowired
	private ResourceOwnerPasswordResourceDetails resourceDetails;
	
	private OAuth2RestTemplate restTemplate = null;
	
	public OAuth2RestTemplate getRestTemplate() {
		if(restTemplate == null) {
			restTemplate = new OAuth2RestTemplate(resourceDetails);
		}
		
		return restTemplate;
	}
	
	public void login(String user, String password) throws OAuth2AccessDeniedException, ResourceAccessException{
		resourceDetails.setUsername(user);
		resourceDetails.setPassword(password);
		getRestTemplate().getForEntity(loginPingURL, String.class);
	}

}
