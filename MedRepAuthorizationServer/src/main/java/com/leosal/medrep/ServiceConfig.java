package com.leosal.medrep;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceConfig extends GlobalAuthenticationConfigurerAdapter{
	
	@Value("${medrep.oauth2.rest.username}")
	private String internalUser;
	
	@Value("${medrep.oauth2.rest.password}")
	private String internalPassword;
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()//.passwordEncoder(passwordEncoder())
		.withUser("leonid").password("pass1").roles("USER", "ADMIN").and()
		.withUser("ilia").password("pass2").roles("USER", "OPERATOR").and()
		.withUser(internalUser).password(internalPassword).roles("USER", "OPERATOR", "ADMIN");
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
