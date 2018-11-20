//package com.leosal.medrep.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
////@EnableWebSecurity
//@Order(-20)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//	}
//	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.requestMatchers()
//          .antMatchers("/login", "/oauth/authorize", "/oauth/token")
//          .and()
//          .authorizeRequests()
//          .anyRequest().authenticated()
//          .and()
//          .formLogin().permitAll();
////		  http
////	      .antMatcher("/**")
////	      .authorizeRequests()
////	        .antMatchers("/oauth/authorize**")
////	        .permitAll()
////	      .anyRequest()
////	        .authenticated();
////		http.formLogin().permitAll().and().authorizeRequests()
////        .antMatchers("/login", "/oauth/authorize**", "/oauth/confirm_access","/oauth/token").permitAll().anyRequest()
////        .authenticated();
//    }
//	
//	
//	
//}
