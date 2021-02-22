package com.gaepom.util;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gaepom.dao.PortfolioRepository;
import com.gaepom.dao.ProjectRecruitRepository;
import com.gaepom.dao.ProjectRepository;
import com.gaepom.dao.UserRepository;
import com.gaepom.domain.User;

@Aspect
@Configuration
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(UserRepository repository, ProjectRecruitRepository repoRecruit,
			ProjectRepository repoProj, PortfolioRepository repoPf) {

		return args -> {
			log.info("Preloading " + repository.save(new User("user1", "11", "유재석", 30, "you@naver.com",
					"010-1111-2222", "서울시 서초구", "Java,Javascript", "you.jpg", "개발자", null, null, null)));
			log.info("Preloading " + repository.save(new User("user2", "11", "강호동", 35, "kang@naver.com",
					"010-3333-4444", "서울시 강남구", "Python,C++", "kang.jpg", "개발자", null, null, null)));
			log.info("Preloading " + repository.save(new User("user3", "11", "신동엽", 40, "sin@naver.com",
					"010-5555-6666", "경기도 부천시", "PPT,Excel", "shin.jpg", "기획자", null, null, null)));
			log.info("Preloading " + repository.save(new User("apple77", "pear123", "도봉순", 30, "apple@naver.com",
					"010-1234-9384", "경기도시흥시", "Java,TensorFlow", "dobong.jpg", "개발자", null, null, null)));
			log.info("Preloading " + repository.save(new User("ch1020", "hello", "최유진", 28, "youlove@naver.com",
					"010-9078-9090", "서울특별시광진구", "MicrosoftSuite,AdobeXD", "choi.jpg", "디자이너", null, null, null)));
		};
	}
}
