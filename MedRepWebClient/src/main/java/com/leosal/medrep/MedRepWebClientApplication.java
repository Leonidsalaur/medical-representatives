package com.leosal.medrep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableResourceServer
@EnableEurekaClient
@ComponentScan(basePackages= {"com.leosal.medrep.configs", "com.leosal.medrep.services", "com.leosal.medrep.controller"})
public class MedRepWebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedRepWebClientApplication.class, args);
	}
}
