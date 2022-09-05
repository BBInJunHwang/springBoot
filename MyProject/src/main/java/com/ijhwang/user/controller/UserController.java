package com.ijhwang.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijhwang.user.dto.UserRequestDto;
import com.ijhwang.user.dto.UserResponseDto;
import com.ijhwang.user.service.UserService;

@RestController
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping("/api/v1/user")
	public UserResponseDto detail(@RequestBody UserRequestDto userRequestDto) {
		return userService.findUser(userRequestDto);
	}
}
