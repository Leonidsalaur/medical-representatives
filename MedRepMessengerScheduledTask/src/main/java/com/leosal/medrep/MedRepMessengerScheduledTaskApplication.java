package com.leosal.medrep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableTask
public class MedRepMessengerScheduledTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedRepMessengerScheduledTaskApplication.class, args);
	}
	
	@Bean
	public MessengerTask messenerTask() {
		return new MessengerTask();
	}
}
