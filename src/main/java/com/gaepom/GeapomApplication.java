package com.gaepom;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


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
