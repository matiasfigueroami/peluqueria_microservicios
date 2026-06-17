package com.peluqueria.ms_agenda_cita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsAgendaCitaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAgendaCitaApplication.class, args);
	}

}
