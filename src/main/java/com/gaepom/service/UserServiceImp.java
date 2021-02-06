package com.gaepom.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.UserRepository;
import com.gaepom.domain.User;
import com.gaepom.exception.UserException;


@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	public User getUser(User User) {
		Optional<User> findUser = userRepo.findById(User.getUserId());
		if (findUser.isPresent()) {
			return findUser.get();
		} else {
			return null;
		}
	}

	public void insertUser(User user) {
		Optional<User> findUser = userRepo.findById(user.getUserId());
		System.out.println("dd");
		if (!findUser.isPresent()) {
			userRepo.save(user);
			System.out.println("가입완료");
			logger.info(user.getUserId() + "회원 가입");
			
		} else {
			throw new UserException("이미 가입되어 있는 회원입니다.");
		}

	}

	@Override
	public <S extends User> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<User> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends User> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findUserByUserid(String userid) {
		// TODO Auto-generated method stub
		return null;
	}



}
