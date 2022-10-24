package com.spring.junitTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * Junit Test ->  DB 연결 상태에서 테스트
 * Security Test -> 테스트
 * 
 * TDD 테스트 주도 방법론 (가장 정석인 방법)
 * 개발 -> Junit Test 작성 -> 수많은 오류 가능성 테스트 (무조건 오류를 발생시킴) -> 수정 -> 본코드
 * 
 * 하지만 보통 이렇게한다..? TDD가 정석이지만.. 필수는 아니고 자기만의 방식으로 해보자!
 * 개발 -> 본코드 -> Junit Test 작성 -> 본코드 수정
 * 
 * localhost:8080/h2-console 
 * 
 * url : jdbc:h2:mem:test 설정
 * username 그대로, pw 없이 testConnection 해서 h2-console 접속 가능하다.
 * 
 * */

@SpringBootApplication
public class JunitTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunitTestApplication.class, args);
	}

}
