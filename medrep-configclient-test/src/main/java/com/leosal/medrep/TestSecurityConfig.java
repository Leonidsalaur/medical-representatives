//package com.leosal.medrep;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled=true)
//public class TestSecurityConfig extends ResourceServerConfigurerAdapter {
//	
//	private static final String LOCALHOST = "127.0.0.1";
//	
//	@Override
//    public void configure(HttpSecurity http) throws Exception {
//		
//		RequestMatcher matcher = new RequestMatcher() {
//			
//			@Override
//			public boolean matches(HttpServletRequest request) {
//				System.out.println("USER_SERVICE_APP Remote host [" + request.getRemoteHost() + "] => Local host [" + LOCALHOST + "]");
//				return !request.getRemoteHost().equals(LOCALHOST);
//				
//			}
//		};
//		
//        http.requestMatcher(matcher).authorizeRequests().anyRequest().authenticated();
//    }
//	
//}
