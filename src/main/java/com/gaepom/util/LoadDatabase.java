package com.gaepom.util;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gaepom.dao.ApplicationRepository;
import com.gaepom.dao.PortfolioRepository;
import com.gaepom.dao.ProjectRepository;
import com.gaepom.dao.UserRepository;
import com.gaepom.domain.Application;
import com.gaepom.domain.Project;
import com.gaepom.domain.User;

@Aspect
@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(UserRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new User("user1", "11", "유재석", 30, "you@naver.com", "010-1111-2222", "서울시 서초구", "Java", "lion.jpg", "개발자", null, null)));
			log.info("Preloading " + repository.save(new User("user2", "11", "강호동", 35, "kang@naver.com", "010-3333-4444", "서울시 강남구", "Python", "lion.jpg", "개발자", null, null)));
			log.info("Preloading " + repository.save(new User("user3", "11", "신동엽", 40, "sin@naver.com", "010-5555-6666", "경기도 부천시", "PPT", "lion.jpg", "기획자", null, null)));
			log.info("Preloading " + repository.save(new User("apple77","pear123","도봉순",30,"apple@naver.com","010-1234-9384","경기도시흥시","Java,TensorFlow,Vue.js,React","lion.jpg","개발자", null, null)));
			log.info("Preloading " + repository.save(new User("ch1020","hello","최유진",28,"youlove@naver.com","010-9078-9090","서울특별시광진구","MicrosoftSuite,AdobeXD","lion.jpg","기획자", null, null)));
		};
	}
	
	@Bean
	CommandLineRunner portfolioInitDatabase(PortfolioRepository portfolioRepo) {

		return args -> {
//			log.info("Preloading " + portfolioRepo.save(new Portfolio(pfSeq, pfSubtitle, pfDuration, pfDescription, participation, pfLang, pfTools, pfDbms, pfLink, pfCategory, userId)));
//			log.info("Preloading " + portfolioRepo.save(new Portfolio(1L, "친환경 업사이클링 쇼핑몰", "2021.01.21-2021.02.30", "업사이클링 제품만을 판매하는 오픈마켓", "참여도1", "Tensorflow", "GitHub,Jira,Jandi", "Oracle", "프로젝트주소1", "웹앱", null)));
//			log.info("Preloading " + portfolioRepo.save(new Portfolio(2L, "캠핑짱짱맨", "2021-03-03-2021-07-21", "캠핑용품을 대여해주고 캠핑 장소를 알려주는 마켓", "참여도2", "Java,Javascript", "GitHub,Jira,Jandi", "MySQL", "프로젝트주소2", "웹앱", null)));
		};
	}
	
	// @Bean
	// CommandLineRunner projectInitDatabase(ProjectRepository projectRepo) {

	// 	return args -> {
	// 		log.info("Preloading " + projectRepo.save(new Project(1L, null, null,  null, "친환경 업사이클링 쇼핑몰", "업사이클링 제품만을 판매하는 오픈마켓", "2021.01.21-2021.02.30", "GitHub, Jira, Jandi", "웹앱", "Tensorflow", "Oracle", 1L)));
	// 		log.info("Preloading " + projectRepo.save(new Project(2L, "kim0713", 2, "캠핑짱짱맨", "캠핑용품을 대여해주고 캠핑 장소를 알려주는 마켓", "2021-03-03-2021-07-21", "GitHub, Jira, Jandi", "웹앱", "Java, Javascript", "MySQL", 2))); 
	// 	};
	// }
}
