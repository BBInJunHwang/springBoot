package com.spring.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication	// 스프링부트 기본적인 설정 
@EnableScheduling	// 스케줄링 활성화
public class RbmqPublisherAppApplication {

	public static void main(String[] args) {	// 스프링 부트는 메인 선언된 클래스 기준으로 실행
		SpringApplication.run(RbmqPublisherAppApplication.class, args);
	}

}
