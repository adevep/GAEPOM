package com.gaepom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration
//@ComponentScan("com.gaepom.controller.ApplicationController")
@EnableJpaRepositories(basePackages="com.gaepom.dao")
@EntityScan("com.gaepom.domain")
@SpringBootApplication
public class GeapomApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeapomApplication.class, args);
	}

}
