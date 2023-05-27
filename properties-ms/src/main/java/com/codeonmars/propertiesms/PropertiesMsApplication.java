package com.codeonmars.propertiesms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PropertiesMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertiesMsApplication.class, args);
	}

}
