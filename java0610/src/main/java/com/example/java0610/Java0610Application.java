package com.example.java0610;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Java0610Application {

	public static void main(String[] args) {
		SpringApplication.run(Java0610Application.class, args);
	}

}
