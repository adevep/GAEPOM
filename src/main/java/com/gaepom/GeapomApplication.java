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
//@ComponentScan(basePackages="ApplicationService")
@EnableJpaRepositories(basePackages="com.gaepom.dao")
@EntityScan("com.gaepom.domain")
@SpringBootApplication
public class GeapomApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeapomApplication.class, args);
	}
	
//	 // Fix the CORS errors
//    @Bean
//    public FilterRegistrationBean simpleCorsFilter() {  
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
//        CorsConfiguration config = new CorsConfiguration();  
//        config.setAllowCredentials(true); 
//        // *** URL below needs to match the Vue client URL and port ***
//        config.setAllowedOrigins(Collections.singletonList("http://localhost:80")); 
//        config.setAllowedMethods(Collections.singletonList("*"));  
//        config.setAllowedHeaders(Collections.singletonList("*"));  
//        source.registerCorsConfiguration("/**", config);  
//        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);  
//        return bean;  
//    }   

}
