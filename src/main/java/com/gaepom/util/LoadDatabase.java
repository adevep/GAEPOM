package com.gaepom.util;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gaepom.dao.PortfolioRepository;
import com.gaepom.dao.ProjectRepository;
import com.gaepom.dao.UserRepository;
import com.gaepom.domain.Portfolio;
import com.gaepom.domain.Project;
import com.gaepom.domain.User;

@Aspect
@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner userInitDatabase(UserRepository userRepo) {

		return args -> {
//			log.info("Preloading " + userRepo.save(new User(userId, password, name, age, email, phoneNum, address, stack, userImage, position, portfolios, projects)));
			log.info("Preloading " + userRepo.save(new User("apple77","pear123","도봉순",30,"apple@naver.com","010-1234-9384","경기도시흥시","Java,TensorFlow,Vue.js,React","img","개발자", null, null)));
			log.info("Preloading " + userRepo.save(new User("kim0713","reoloves2","김철수",29,"reolove7029@gmail.com","010-3843-8926","서울특별시마포구","Java,JavaScript,Vue.js","img","개발자", null, null)));
			log.info("Preloading " + userRepo.save(new User("ch1020","hello","최유진",28,"youlove@naver.com","010-9078-9090","서울특별시광진구","MicrosoftSuite,AdobeXD","img","기획자", null, null)));
			log.info("Preloading " + userRepo.save(new User("hello10","youtoo","이재민",25,"jaemin88@gamil.com","010-4145-9078","경기도수원시","Html,CSS","img","웹퍼블리셔", null, null)));
			log.info("Preloading " + userRepo.save(new User("ayozizi89","aabbcc909","김영희",27,"soyeong12@naver.com","010-2333-8090","부산광역시해운대구","Photoshop,Paintshop,Illustrator","img","웹디자이너", null, null)));
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

	@Bean
	CommandLineRunner projectInitDatabase(ProjectRepository projectRepo) {
		
		return args -> {
//			log.info("Preloading " + projectRepo.save(new Project(pjSeq, recSeq, trackSeq, userId, pjTitle, pjDescription, pjDuration, pjTools, pjCategory, pjLang, pjDbms, applications)));
			log.info("Preloading " + projectRepo.save(new Project(1L, null, null, null, "친환경 업사이클링 쇼핑몰", "업사이클링 제품만을 판매하는 오픈마켓", "2021.01.21-2021.02.30", "GitHub, Jira, Jandi", "웹앱", "Tensorflow", "Oracle", null)));
			log.info("Preloading " + projectRepo.save(new Project(2L, null, null, null, "캠핑짱짱맨", "캠핑용품을 대여해주고 캠핑 장소를 알려주는 마켓", "2021-03-03-2021-07-21", "GitHub, Jira, Jandi", "웹앱", "Java, Javascript", "MySQL", null)));
		};
	}
}
