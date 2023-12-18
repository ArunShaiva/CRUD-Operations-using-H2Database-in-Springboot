package com.example.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudUsingH2DatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudUsingH2DatabaseApplication.class, args);
		System.out.println("Ran succesfully");
	}

}
