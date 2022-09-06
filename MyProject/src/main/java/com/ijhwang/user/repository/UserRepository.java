package com.ijhwang.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijhwang.jwt.model.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String> {
	
	public Optional<UserInfo> findByUserId(String username);
	
	public Optional<UserInfo> deleteByUserId(String username);
}
