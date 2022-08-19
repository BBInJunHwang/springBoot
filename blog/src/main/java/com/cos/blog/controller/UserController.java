package com.cos.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 인증이 안된 사용자들이 출입 경로 /auth/**
// / 이면 index.jsp
// static 아래 /js, css 등 허용 


@RestController
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return null;
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return null;
	}
}
