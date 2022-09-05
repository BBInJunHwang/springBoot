package com.ijhwang.jwt.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ijhwang.jwt.model.UserInfo;
import com.ijhwang.jwt.repository.JwtRepository;

import lombok.RequiredArgsConstructor;

// localhost:8083/login 일떄 호출된다 -> 기본 로그인 주소

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final JwtRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("custom UserDetails Service");
		UserInfo userEntity = userRepository.findByUserId(userId);
		
		return new PrincipalDetails(userEntity);
	}

}
