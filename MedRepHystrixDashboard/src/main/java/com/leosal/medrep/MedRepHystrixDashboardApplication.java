package com.leosal.medrep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class MedRepHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedRepHystrixDashboardApplication.class, args);
	}
}
