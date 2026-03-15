package com.gregoriofer.accesodatos.ejemplo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Proyecto de Acceso a Datos", version = "1.0",
		description = "Proyecto de ejemplo"))
public class ProyectoDeEjemploApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoDeEjemploApplication.class, args);
	}

}
