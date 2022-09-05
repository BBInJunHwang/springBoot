package com.ijhwang.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijhwang.jwt.model.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String> {
	
	public UserInfo findByUserId(String username);
}
