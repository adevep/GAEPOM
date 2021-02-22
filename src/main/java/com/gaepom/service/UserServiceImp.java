package com.gaepom.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
	private UserRepository userrepo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public User login(String userid, String password) {

		Optional<User> findUser = userrepo.findById(userid);

		if (findUser.isPresent()) {
			if (findUser.get().getPassword().equals(password)) {
				logger.info("{} 회원 로그인 성공", userid);
				return findUser.get();
			} else {
				logger.error("{} 회원 로그인 비밀번호 불일치");
				throw new UserNotFoundException("비밀번호를 확인해주세요.");
			}
		} else {
			logger.error("{} 미존재 회원 로그인 시도");
			throw new UserNotFoundException("해당 ID로 가입된 회원이 존재하지 않습니다");
		}
	}

	public User getUser(String userid) {

		Optional<User> findUser = userrepo.findById(userid);

		if (findUser.isPresent()) {
			logger.info("{} 회원 조회 요청 성공", userid);
			return findUser.get();
		} else {
			logger.error("미존재 회원 {} 조회 요청 실패", userid);
			throw new UserNotFoundException("해당하는 아이디의 회원이 없습니다.");
		}
	}

	public List<User> getUserList() {

		logger.info("전체 회원 조회 요청");
		return (List<User>) userrepo.findAll();

	}

	public User insertUser(User user, String[] stacklist, MultipartFile mfile) {

		Optional<User> findUser = userrepo.findById(user.getUserId());

		if (!findUser.isPresent()) {

			String imgname = null;

			try {
				imgname = String.valueOf(System.currentTimeMillis()) + mfile.getOriginalFilename();
				mfile.transferTo(new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\upload\\" + imgname));
				logger.info("{} 가입회원 이미지 등록", user.getUserId());
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				logger.error("{} 가입회원 이미지 등록 오류", user.getUserId());
			}
			user.setUserImage(imgname);
			user.setStack(String.join(",", stacklist));

			userrepo.save(user);

			logger.info("{} 회원 가입완료", user.getUserId());
			return user;
		} else {
			logger.error("{} 기존가입 ID 가입실패 ", user.getUserId());
			throw new UserException("해당 ID는 이미 가입된 회원입니다.");
		}
	}

	public User insertUserNoimg(User user, String[] stacklist) {

		Optional<User> findUser = userrepo.findById(user.getUserId());

		if (!findUser.isPresent()) {

			user.setUserImage("default.png");
			user.setStack(String.join(",", stacklist));

			userrepo.save(user);
			logger.info("{} 회원 가입 완료", user.getUserId());
			return user;
		} else {
			logger.error("{} 기존가입 ID 가입실패 ", user.getUserId());
			throw new UserException("해당 ID는 이미 가입된 회원입니다.");
		}
	}

	public User findUserByUserId(String userid) {
		Optional<User> findUser = userrepo.findById(userid);
		if (findUser.isPresent()) {
			logger.info("{} 회원 조회 요청 성공", userid);
			return findUser.get();
		} else {
			logger.error("미존재 회원 {} 조회 요청 실패", userid);
			throw new UserNotFoundException("해당하는 아이디의 유저가 없습니다.");
		}
	}

	public User updateUser(User user, String[] stacklist, MultipartFile mfile) {

		Optional<User> findUser = userrepo.findById(user.getUserId());

		if (findUser.isPresent()) {

			String imgname = null;

			try {
				imgname = String.valueOf(System.currentTimeMillis()) + mfile.getOriginalFilename();
				mfile.transferTo(new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\upload\\" + imgname));

				String filename = findUser.get().getUserImage();
				File file = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\upload\\" + filename);

				if (file.exists() && !filename.equals("default.png")) {
					if (file.delete()) {
						logger.info("{} 회원 기존 이미지 삭제 완료", user.getUserId());
					} else {
						logger.debug("{} 회원 기존 이미지 삭제 실패", user.getUserId());
					}
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				logger.error("{} 회원 이미지 갱신 오류 발생", user.getUserId());
			}
			findUser.get().setPassword(user.getPassword());
			findUser.get().setName(user.getName());
			findUser.get().setAge(user.getAge());
			findUser.get().setEmail(user.getEmail());
			findUser.get().setPhoneNum(user.getPhoneNum());
			findUser.get().setAddress(user.getAddress());
			findUser.get().setStack(String.join(",", stacklist));
			findUser.get().setUserImage(imgname);
			findUser.get().setPosition(user.getPosition());

			userrepo.save(findUser.get());
			logger.info("{} 회원 정보 갱신 완료", user.getUserId());
			return findUser.get();
		} else {
			logger.error("미존재 회원 {} 갱신 요청 실패", user.getUserId());
			throw new UserNotFoundException("해당하는 아이디의 유저가 없습니다.");
		}
	}

	public User updateUserNoimg(User user, String[] stacklist) {
		Optional<User> findUser = userrepo.findById(user.getUserId());
		if (findUser.isPresent()) {

			String filename = findUser.get().getUserImage();
			File file = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\upload\\" + filename);

			if (file.exists() && !filename.equals("default.png")) {
				if (file.delete()) {
					logger.info("{} 회원 기존 이미지 삭제 완료", user.getUserId());
				} else {
					logger.debug("{} 회원 기존 이미지 삭제 실패", user.getUserId());
				}
			}
			findUser.get().setPassword(user.getPassword());
			findUser.get().setName(user.getName());
			findUser.get().setAge(user.getAge());
			findUser.get().setEmail(user.getEmail());
			findUser.get().setPhoneNum(user.getPhoneNum());
			findUser.get().setAddress(user.getAddress());
			findUser.get().setStack(String.join(",", stacklist));
			findUser.get().setUserImage("default.png");
			findUser.get().setPosition(user.getPosition());

			userrepo.save(findUser.get());
			logger.info("{} 회원 정보 갱신 완료", user.getUserId());
			return findUser.get();
		} else {
			logger.error("미존재 회원 {} 갱신 요청 실패", user.getUserId());
			throw new UserNotFoundException("유저가 없습니다.");
		}
	}

	public User updateUserTliked(String userid, String tliked) {

		Optional<User> findUser = userrepo.findById(userid);

		if (findUser.isPresent()) {
			findUser.get().setTLiked(tliked);

			userrepo.save(findUser.get());
			logger.info("{} 회원 좋아요 수정", findUser.get().getUserId());
			return findUser.get();
		} else {
			logger.error("미존재 회원 {} 갱신 요청 실패", findUser.get().getUserId());
			throw new UserNotFoundException("해당 ID의 유저가 없습니다.");
		}
	}

	public void deleteUser(String userid) {

		Optional<User> findUser = userrepo.findById(userid);

		if (findUser.isPresent()) {

			String filename = findUser.get().getUserImage();
			File file = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\upload\\" + filename);

			if (file.exists() && !filename.equals("default.png")) {
				if (file.delete()) {
					logger.info("{} 회원 기존 이미지 삭제 완료", findUser.get().getUserId());
				} else {
					logger.error("{} 회원 기존 이미지 삭제 실패", findUser.get().getUserId());
				}
			}
			userrepo.delete(findUser.get());
			logger.info("{} 회원 탈퇴 완료", userid);
		} else {
			logger.error("미존재 회원 {} 탈퇴 요청 실패", findUser.get().getUserId());
			throw new UserNotFoundException("해당하는 ID의 유저가 없습니다");
		}
	}

}
