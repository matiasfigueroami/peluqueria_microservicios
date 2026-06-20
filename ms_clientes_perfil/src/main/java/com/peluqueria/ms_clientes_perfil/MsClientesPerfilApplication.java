package com.peluqueria.ms_clientes_perfil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsClientesPerfilApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsClientesPerfilApplication.class, args);
	}

}
