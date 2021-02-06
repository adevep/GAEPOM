package com.gaepom.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.GUserRepository;
import com.gaepom.domain.GUser;
import com.gaepom.exception.UserException;


@Service
public class GUserServiceImp implements GUserService {
	
	@Autowired
	private GUserRepository guserRepo;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	public GUser getGUser(GUser guser) {
		Optional<GUser> findGUser = guserRepo.findById(guser.getUserId());
		if (findGUser.isPresent()) {
			return findGUser.get();
		} else {
			return null;
		}
	}

	public void insertGUser(GUser guser) {
		Optional<GUser> findGUser = guserRepo.findById(guser.getUserId());
		System.out.println("dd");
		if (!findGUser.isPresent()) {
			guserRepo.save(guser);
			System.out.println("가입완료");
			logger.info(guser.getUserId() + "회원 가입");
			
		} else {
			throw new UserException("이미 가입되어 있는 회원입니다.");
		}

	}
}
