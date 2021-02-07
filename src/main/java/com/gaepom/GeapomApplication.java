package com.gaepom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
