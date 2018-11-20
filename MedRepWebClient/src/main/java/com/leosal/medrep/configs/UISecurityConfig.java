package com.leosal.medrep.configs;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class UISecurityConfig extends WebSecurityConfigurerAdapter {
	 @Override
	  protected void configure(HttpSecurity http) throws Exception {
//		 http.formLogin().permitAll().and().authorizeRequests()
//         .antMatchers("/login**","/index.html").permitAll().anyRequest()
//         .authenticated().and().csrf().disable();
		 
		 http.formLogin().loginPage("/index.xhtml").permitAll()
		 .and()
		 .authorizeRequests()
		 //.antMatchers("/login", "/index.xhtml").permitAll()
		 .anyRequest().authenticated();
	    System.out.println("UISecurityConfig.HttpSecurity configured");
	  }
}
