package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 사용자요청 -> data 응답
@RestController
public class HttpControllerTest {
	
	private static final String TAG="HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//Member m = new Member(1,"ij","1234","ijhwang@naver.com");
		
		// @Builder 사용
		// id 값을 시퀀스로 사용하고 싶을때 생성자를 새롭게 만들 필요없다.
		// 생성자의 필드 순서 안지켜도 된다.
		Member m = Member.builder().username("ijhwang").password("1234").email("ijhwang@naver.com").build();
		System.out.println(TAG + "getter : " +m.getUsername());
		// ctrl + d 복사
		m.setUsername("ijhwang2");
		System.out.println(TAG + "setter : " +m.getUsername());
		
		return "lombok test 완료 ";
	}
	

	@GetMapping("/http/get")
	public String getTest(Member m) {
		
		return "get요청 : " + m.getId();
		
	}
}
