package com.ijhwang.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijhwang.jwt.model.UserInfo;

// CRUD 함수를 JpaRepository 가 들고있음
// @Repository annotation 없어도 IOC 된다 => JpaRepository를 상속했기 때문에 빈으로 등록됨
// model, pk
public interface JwtRepository extends JpaRepository<UserInfo, Integer> {
	
	public UserInfo findByUserId(String username);
	

}
