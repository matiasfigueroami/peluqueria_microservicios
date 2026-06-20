package com.peluqueria.ms_resenas_feedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsResenasFeedbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsResenasFeedbackApplication.class, args);
	}

}
