package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class BookStoreCartApiApplication {

	  @Bean
	    public RestTemplate getRestTemplate() {
	        return new RestTemplate();
	    }

	public static void main(String[] args) {
		SpringApplication.run(BookStoreCartApiApplication.class, args);
		
		 System.out.println("Hello!!");
	        log.info("Welcome to program");
	}

}
