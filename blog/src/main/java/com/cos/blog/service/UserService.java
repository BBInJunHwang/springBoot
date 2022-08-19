package com.cos.blog.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cos.blog.model.UserTB;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {
	// Service 목적 : 트랜잭션관리 1개이상 트랜잭션을 묶어서 하나의 트랜잭션으로 사용해서 commit / rollback 한다.
	// Dao 목적 : CRUD 처리 
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional // 트랜잭션관리한다.
	public void UserRegist(UserTB user) {
		userRepository.save(user);
	}

	/*
	 * @Transactional(readOnly = true) // select 시 트랜잭션 시작, 서비스 종료 시 트랜잭션 종료(정합성 유지
	 * 가능, select 여러번 해도 같은 데이터 보여줌) public UserTB login(UserTB user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
}
