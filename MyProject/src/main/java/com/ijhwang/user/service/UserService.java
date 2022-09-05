package com.ijhwang.user.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ijhwang.jwt.model.TeamInfo;
import com.ijhwang.jwt.model.UserInfo;
import com.ijhwang.user.dto.UserRequestDto;
import com.ijhwang.user.dto.UserResponseDto;
import com.ijhwang.user.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;
	
//	public UserService(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}

	@Transactional
	public UserResponseDto findUser(UserRequestDto userRequestDto) {
		
		UserInfo userInfo = userRepository.findByUserId(userRequestDto.getUserId());
		//UserResponseDto userResponseDto = new UserResponseDto(userInfo);
		
		return null;
		
	}
}
