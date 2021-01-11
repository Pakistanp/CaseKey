package com.project.CaseKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class CaseKeyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseKeyApplication.class, args);
	}

}
