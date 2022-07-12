package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		
		/**
		 스프링은 @controller 붙을 때 파일을 리턴한다.
		 static 경로에는 브라우저에서 인식 가능한 정적 파일만 넣어야한다.
		 html , js, css ,img etc
		 파일리턴 기본경로 : src/main/resources/static
		 리턴명 : /파일명
		 풀경로 : src/main/resources/static/home.html
		 */
		return "/home.html";

	}
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/a.png";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		
		/**
		 static 경로내 jsp 파일이 존재해도 찾지를 못한다.
		 jsp는 정적 파일이 아니다.
		 동적 자바 파일임 -> 컴파일이 일어나야한다.
		 브라우저는 인식하지 못한다.
		 사용하기 위해서는 
		 prefix : /WEB-INF/views 설정이 필요하다.
		 suffix : .jsp
		 풀네임 : /WEB-INF/views/test.jsp
		 톰캣에서는 jsp 파일이면 웹서버(아파치 등) 웹에서 처리 불가능하다. 
		 톰캣에서 컴파일 해서 html로 던져줄테니까 브라우저에서 이해 가능할거다 라고 처리됨
		 */
		return "test";
	}
	
}
