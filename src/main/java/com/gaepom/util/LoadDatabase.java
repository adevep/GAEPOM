package com.gaepom.util;


import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

//	@Bean
//	CommandLineRunner initDatabase(MemberRepository repository) {
//
//		return args -> {
//			log.info("Preloading " + repository.save(new Member("user1", "11", "유재석", "감성지킴이")));
//			log.info("Preloading " + repository.save(new Member("user2", "22", "백종원", "건강지킴이")));
//		};
//	}
}