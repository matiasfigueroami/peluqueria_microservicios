package com.peluqueria.ms_especialidades_catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsEspecialidadesCatalogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEspecialidadesCatalogoApplication.class, args);
	}

}
