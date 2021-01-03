package com.my.workmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class WorkManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkManagerApplication.class, args);
	}

}
