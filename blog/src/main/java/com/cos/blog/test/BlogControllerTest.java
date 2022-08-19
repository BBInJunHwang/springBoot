package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 스프링이 com.cos.blog 패키지 이해를 스캔해서 
// 모든 파일을 메모리에 new 하는것은 아니고 
// 특정 어노테이션이 붙어있는 클래스 파일들을 new 해서(IOC) 스프링 컨테이너에서 관리한다. 메모리에 띄워준다. 서버시작 시

@RestController
public class BlogControllerTest {

	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
}