package com.gaepom.util;

import java.util.Date;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gaepom.dao.ProjectRecruitRepository;
import com.gaepom.dao.ProjectRepository;
import com.gaepom.dao.UserRepository;
import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectRecruit;

import com.gaepom.dao.ApplicationRepository;
import com.gaepom.dao.UserRepository;
import com.gaepom.domain.Application;

import com.gaepom.domain.User;

@Aspect
@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(UserRepository repository, ProjectRecruitRepository repoRecruit, ProjectRepository repoProj ) {

		return args -> {
			log.info("Preloading " + repository.save(new User("user1", "11", "유재석", 30, "you@naver.com", "010-1111-2222", "서울", "Java,Javascript", "default.png", "개발자", null, null)));
			log.info("Preloading " + repository.save(new User("user2", "11", "강호동", 35, "kang@naver.com", "010-3333-4444", "서울", "C,Java", "default.png", "개발자", null, null)));
			log.info("Preloading " + repository.save(new User("user3", "11", "신동엽", 40, "sin@naver.com", "010-5555-6666", "경기", "Gloo maps,Axure", "default.png", "기획자", null, null)));
			log.info("Preloading " + repository.save(new User("apple77","pear123","도봉순",30,"apple@naver.com","010-1234-9384","경기","Python,TensorFlow,R,SQL","default.png","개발자", null, null)));
			log.info("Preloading " + repository.save(new User("seulgi94","hello","강슬기",28,"redvelBear@naver.com","010-9078-9090","서울","Sketch,Adobe XD","default.png","디자이너", null, null)));

			ProjectRecruit recProj = new ProjectRecruit(1L, 5, "개발자,개발자,기획자,디자이너,마케터", "경기", "IPO시 주식상여", 0, "2021.02.02-2022.02.15", new Date(), null);
			log.info("Preloading " + repoRecruit.save(recProj));
			
			log.info("Preloading " + repoProj.save(new Project(10L,
					recProj,
					null,
					new User("user1", "11", "유재석", 30, "you@naver.com", "010-1111-2222", "서울시 서초구", "Java", "default.png", "개발자", null, null),
					"친환경 업사이클링 쇼핑몰", "업사이클링 제품만을 판매하는 오픈마켓", "2021.01.21-2021.02.30", "GitHub, Jira, Jandi", "웹앱", "Tensorflow", "Oracle", null)));
		
			};

	}
}
