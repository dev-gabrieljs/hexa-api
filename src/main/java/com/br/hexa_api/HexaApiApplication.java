package com.br.hexa_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.br.hexa_api.adapter")
public class HexaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexaApiApplication.class, args);
	}

}
