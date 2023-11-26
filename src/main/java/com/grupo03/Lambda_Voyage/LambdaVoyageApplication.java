package com.grupo03.Lambda_Voyage;

import com.grupo03.Lambda_Voyage.domain.repositories.mongo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LambdaVoyageApplication {

	public static void main(String[] args) {
		SpringApplication.run(LambdaVoyageApplication.class, args);
	}


}
