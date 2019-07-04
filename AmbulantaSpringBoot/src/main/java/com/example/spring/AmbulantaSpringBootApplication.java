package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("model")
public class AmbulantaSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmbulantaSpringBootApplication.class, args);
	}
	

}
