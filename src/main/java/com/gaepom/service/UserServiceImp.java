package com.gaepom.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	public void insertUser(User user, MultipartFile mfile) {
		
		Optional<User> findUser = userRepo.findById(user.getUserId());
		if (!findUser.isPresent()) {
			
			// 경로 받아줄 변수 선언
			String imgname = null;
			
			try {
				// GAEPOM까지 각자 다른 경로  = System.getProperty("user.dir")
				// 파일 이름 지정 -> 원래 이미지 파일 이름 (= mfile.getOriginalFilename()) + 이미지 파일 이름 중복되는 경우 막기위한 현재시간  (= System.currentTimeMillis())
				// 위에 두가지 더해서  -> (GAEPOM까지 각자의 경로)/src/main/webapp/upload/파일 이름(원래 파일 이름 + 이미지 업로드 시간)으로 해당 경로 폴더에 이미지 파일 생성
				// 출력시엔 url로
				imgname = String.valueOf(System.currentTimeMillis()) + mfile.getOriginalFilename();
				mfile.transferTo(new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\upload\\" + imgname));
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				logger.debug("파일 등록 실패");
			} 
			
			// user객체 테이블에 넣기전에 이미지 경로를 userimage컬럼에 넣어준 후에 테이블에 저장하기
			user.setUserImage(imgname);
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

	






}