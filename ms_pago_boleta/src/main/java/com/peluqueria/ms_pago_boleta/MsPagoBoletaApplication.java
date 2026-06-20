package com.peluqueria.ms_pago_boleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsPagoBoletaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPagoBoletaApplication.class, args);
	}

}
