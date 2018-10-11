package com.leosal.medrep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@EnableZuulProxy
@SpringBootApplication
//@EnableDiscoveryClient
//@EnableResourceServer
//@EnableOAuth2Sso
@ComponentScan(basePackages= {"com.leosal.medrep.configs"})
public class MedRepZuulProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedRepZuulProxyApplication.class, args);
	}
}
