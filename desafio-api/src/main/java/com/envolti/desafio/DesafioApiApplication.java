package com.envolti.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.envolti.desafio")
@SpringBootApplication
public class DesafioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApiApplication.class, args);
	}
	
}
