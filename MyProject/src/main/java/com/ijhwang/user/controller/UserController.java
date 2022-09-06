package com.ijhwang.user.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijhwang.common.domain.ResponseDto;
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
	
	@PostMapping("/api/v1/user")
	public ResponseDto<Object> saveUser(@RequestBody UserRequestDto userRequestDto) {
		userService.saveUser(userRequestDto);
		
		return new ResponseDto<Object>(200,null);
		
	}
	
	@PutMapping("/api/v1/user")
	public ResponseDto<Object> updateUser(@RequestBody UserRequestDto userRequestDto) {
		userService.updateUser(userRequestDto);
		
		return new ResponseDto<Object>(200,null);
		
	}
	
	@DeleteMapping("/api/v1/user")
	public ResponseDto<Object> deleteUser(@RequestBody UserRequestDto userRequestDto) {
		userService.deleteUser(userRequestDto);
		
		return new ResponseDto<Object>(200,null);
		
	}
}
