//package com.leosal.medrep.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//
//@Configuration
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Value("${security.oauth2.client.client-id}")
//	private String clientId;
//	
//	@Value("${security.oauth2.client.client-secret}")
//	private String clientSecret;
//	
//	@Override
//    public void configure(
//      AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.tokenKeyAccess("permitAll()");
//    }
// 
////    @Override
////    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
////        clients.inMemory()
////          .withClient(clientId)
////          .secret(passwordEncoder.encode(clientSecret))
////          .authorizedGrantTypes("password")
////          .scopes("data_read")
////          .autoApprove(true); 
////        
////        System.out.println("Custom LOG: " + clientId +" :: " + clientSecret);
////    }
//}
