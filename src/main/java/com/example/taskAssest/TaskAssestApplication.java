package com.example.taskAssest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TaskAssestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskAssestApplication.class, args);
	}

}
