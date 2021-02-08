package com.gaepom.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.SessionStatus;

import com.gaepom.dao.UserRepository;
import com.gaepom.domain.User;
import com.gaepom.exception.UserException;
import com.gaepom.exception.UserNotFoundException;


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
			throw new UserNotFoundException("해당 회원을 찾을수 없습니다.");
		}
	}
	

	public List<User> getUserList(User User) {
		Optional<User> findUser = userRepo.findById(User.getUserId());
		if (findUser.isPresent()) {
			return (List<User>) userRepo.findAll();
		} else {
			throw new UserNotFoundException("해당 회원 목록을 찾을수 없습니다.");
		}
	}

	public void insertUser(User user) {
		Optional<User> findUser = userRepo.findById(user.getUserId());
		if (!findUser.isPresent()) {
			userRepo.save(user);
			logger.info(user.getUserId() + " 회원 가입");
			
		} else {
			throw new UserException("이미 가입되어 있는 회원입니다.");
		}

	}
	
	public User findUserByUserId(String userid) {
		Optional<User> findUser = userRepo.findById(userid);
		if (findUser.isPresent()) {
			return findUser.get();
		} else {
			throw new UserNotFoundException("해당 회원을 찾을수 없습니다.");
		}
	}
	

	public void updateUser(User user) {
		Optional<User> findUser = userRepo.findById(user.getUserId());
		if (findUser.isPresent()) {
						
			findUser.get().setPassword(user.getPassword());
			findUser.get().setName(user.getName());
			findUser.get().setAge(user.getAge());
			findUser.get().setEmail(user.getEmail());
			findUser.get().setPhoneNum(user.getPhoneNum());
			findUser.get().setAddress(user.getAddress());
			findUser.get().setStack(user.getStack());
			findUser.get().setUserImage(user.getUserImage());
			findUser.get().setPosition(user.getPosition());
			
			userRepo.save(findUser.get());
			logger.info(user.getUserId() + " 회원 정보 수정");
		} else {
			throw new UserNotFoundException("해당 회원을 찾을수 없습니다.");		
		}
	}
	
	public void deleteUser(User user) {
		Optional<User> findUser = userRepo.findById(user.getUserId());
		if (findUser.isPresent()) {
			userRepo.delete(findUser.get());
			logger.info(user.getUserId() + " 회원 탈퇴");
			
		} else {
			throw new UserNotFoundException("해당 회원을 찾을수 없습니다.");
		}
	}

	////////////////////////////////////////////////////////
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

	






}
