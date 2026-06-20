package com.peluqueria.ms_autenticacion_usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsAutenticacionUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAutenticacionUsuarioApplication.class, args);
	}

}
