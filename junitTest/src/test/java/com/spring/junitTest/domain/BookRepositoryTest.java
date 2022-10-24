package com.spring.junitTest.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * 
 * 테스트란?
 * 
 * 스프링 부트 실행 시 스프링 부트 전체 설정 및 전체 실행
 * 
 * 테스트 실행 시 스프링 부트가 아닌 테스트 환경이 실행됨
 * 이때 controller, service, repository 모두 띄우는걸 통합 테스트라고 한다.
 * 
 * 테스트 환경에서는 repository 처럼 필요한 환경만 메모리에 띄우는 단위테스트를 한다.
 * 
 * */

@DataJpaTest // DB 관련된 component 만 메모리에 로드 된다, Controller,service 안 띄운다.
public class BookRepositoryTest {
	
	/**
	 * 1. 책 등록
	 * 2. 책 목록보기
	 * 3. 책 한권 보기
	 * 4. 책 수정
	 * 5. 책 삭제
	 * 
	 * */
	
	@Autowired	// 테스트 시에는 @Autowired 를 쓰는게 좋다더라.., 생성자 주입말고 
	private BookRepository bookRepository;
	
	
	@Test
	public void 책등록Test() {
		System.out.println("책 등록 Test 실행");
	}

}
