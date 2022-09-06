package com.ijhwang.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijhwang.jwt.model.UserInfo;
import com.ijhwang.user.dto.UserRequestDto;
import com.ijhwang.user.dto.UserResponseDto;
import com.ijhwang.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;

	@Transactional
	public UserResponseDto findUser(UserRequestDto userRequestDto) {
		UserInfo userInfo = userRepository.findByUserId(userRequestDto.getUserId()).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없습니다.");
		});
		UserResponseDto userResponseDto = new UserResponseDto(userInfo);

		return userResponseDto;
	}
	
	@Transactional
	public void saveUser(UserRequestDto userRequestDto) {
		userRepository.save(userRequestDto.toEntity());
	}
	
	@Transactional
	public void updateUser(UserRequestDto userRequestDto) {
		Optional<UserInfo> userInfo = userRepository.findById(userRequestDto.getUserId());
		userInfo.ifPresent(selectUser ->{
			selectUser.update(userRequestDto);
		});
	}
	
	@Transactional
	public void deleteUser(UserRequestDto userRequestDto) {
		Optional<UserInfo> userInfo = userRepository.findById(userRequestDto.getUserId());
		userInfo.ifPresent(selectUser ->{
			selectUser.delete();
		});
	}
}
