package com.peluqueria.ms_peluqueros_staff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsPeluquerosStaffApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPeluquerosStaffApplication.class, args);
	}

}
