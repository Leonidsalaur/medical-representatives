package com.leosal.medrep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;



@SpringBootApplication
@EnableZipkinServer
public class MedRepZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedRepZipkinServerApplication.class, args);
	}
}
