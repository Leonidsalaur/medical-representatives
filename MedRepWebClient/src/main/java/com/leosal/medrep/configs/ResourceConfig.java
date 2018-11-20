//package com.leosal.medrep.configs;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
//
//import com.leosal.medrep.utils.CustomUserInfoTokenServices;
//
//@Configuration
//public class ResourceConfig {
//	@Autowired
//	private ResourceServerProperties sso;
//	
//	@Bean
//	public ResourceServerTokenServices userInfoTokenServices() {
//		return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
//	}
//
//}
