package com.posco.pcommentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PcommentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcommentServiceApplication.class, args);
	}

}
