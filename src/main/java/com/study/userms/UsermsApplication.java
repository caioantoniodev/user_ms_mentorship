package com.study.userms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class UsermsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsermsApplication.class, args);
	}

}
