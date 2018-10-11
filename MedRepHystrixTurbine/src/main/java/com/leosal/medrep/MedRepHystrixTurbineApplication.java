package com.leosal.medrep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@EnableTurbineStream
@SpringBootApplication
public class MedRepHystrixTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedRepHystrixTurbineApplication.class, args);
	}
}
