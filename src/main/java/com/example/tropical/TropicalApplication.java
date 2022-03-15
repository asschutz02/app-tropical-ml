package com.example.tropical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TropicalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TropicalApplication.class, args);
	}
}
