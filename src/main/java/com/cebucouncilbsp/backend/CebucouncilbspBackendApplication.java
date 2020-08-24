package com.cebucouncilbsp.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cebucouncilbsp.backend.repository")
public class CebucouncilbspBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CebucouncilbspBackendApplication.class, args);
	}

}
