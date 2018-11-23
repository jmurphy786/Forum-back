package com.qa.SweetSpot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SweetSpotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SweetSpotApplication.class, args);
	}
}
