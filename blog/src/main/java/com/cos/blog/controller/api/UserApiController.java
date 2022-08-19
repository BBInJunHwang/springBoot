package com.cos.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.controller.api.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.UserTB;
import com.cos.blog.service.UserService;

@RestController // data만 반환
public class UserApiController {
	
	// component-scan 시 @Service 등 빈으로 등록된 어노테이션 스캔 후 IOC 메모리에 띄움
	private UserService userService;
	
	public UserApiController(UserService userService) {
		this.userService = userService;
	}
	
	//@Autowired // httpSession은 스프링 자체가 IOC에 빈으로 등록해놓는다.
	//private HttpSession session;


	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody UserTB user) {
		System.out.println("UserApiController 호출");
		user.setRole(RoleType.USER);
		userService.UserRegist(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	// 전통적인 로그인 방식
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody UserTB user, HttpSession session){ // 이렇게 적기만 HttpSession이 해도 DI가 된다.
//		System.out.println("Login Controller 호출");
//		UserTB principal = userService.login(user); // principal 접근 주체
//		
//		if(null != principal) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//	}

}
