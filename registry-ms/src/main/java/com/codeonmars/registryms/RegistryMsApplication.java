package com.codeonmars.registryms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistryMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistryMsApplication.class, args);
	}

}
