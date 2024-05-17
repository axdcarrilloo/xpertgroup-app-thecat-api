package com.thecat.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AppThecatApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppThecatApiApplication.class, args);
	}

}
