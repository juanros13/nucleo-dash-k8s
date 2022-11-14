package com.nucleo.dash.msvc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NucleoDashMsvcUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(NucleoDashMsvcUserApplication.class, args);
	}

}
