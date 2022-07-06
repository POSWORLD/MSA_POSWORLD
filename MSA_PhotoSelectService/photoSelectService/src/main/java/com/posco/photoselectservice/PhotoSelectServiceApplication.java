package com.posco.photoselectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PhotoSelectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoSelectServiceApplication.class, args);
	}

}
