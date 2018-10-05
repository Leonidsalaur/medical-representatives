package com.leosal.medrep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.launcher.annotation.EnableTaskLauncher;

@SpringBootApplication
@EnableTaskLauncher
public class TaskSyncTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSyncTestApplication.class, args);
	}
}
