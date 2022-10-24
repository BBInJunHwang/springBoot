package com.spring.junitTest.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	
	
	/**
	 * 테스트를 위해 준비해야하는 절차
	 * 
	 * 1. given(데이터 준비)
	 * 2. when (테스트 실행)
	 * 3. then (검증)
	 * 
	 * */
	
	@Test
	public void 책등록Test() {
		
		// controller, service 메모리에 띄는게 아니기 때문에 직접 데이터(request)를 준비해야한다.
		// 1. given (데이터 준비)
		String title = "Junit5";
		String author = "ijhwang";
		
		// Repository 에서는 이미 Service에서 Dto -> Entity로 변환되어서 값이 들어왔다 가정하고 Book 빌더한다.
		Book book = Book.builder().title(title).author(author).build();
		
		// 2. when (테스트 실행)
		Book bookPS = bookRepository.save(book);	// client에서 받은 book이 DB에 저장이 되고, PK id가 생성되며, save 함수가 DB 저장된 Book을 리턴한다.(DB에 저장된 값과 동기화 된 데이터)
		// ps -> persistence 영구적 저장 의미, book 은 client 통해서 받은값이지만, bookPS는 DB로 부터 영구적으로(영속화 된 데이터) 저장된 값을 의미
		
		// 3. then (검증)
		assertEquals(title, bookPS.getTitle());	// 기대값, 실제값 순서로 같은지 assertEquals 로 검증한다.
		assertEquals(author, bookPS.getAuthor());
		
		
		// 4. 실행 후 debug console 에서 녹색으로 뜨면 검증성공 assertEquals 성공 의미
		// 실제 하나하나 값을 찍어봐도 되지만.. 비지니스 로직이 엄청 복잡 가정하에 전부 확인 할 수 없으니 assertEquals 통과 시 성공으로 가정하고 넘어간다.
	}

}
