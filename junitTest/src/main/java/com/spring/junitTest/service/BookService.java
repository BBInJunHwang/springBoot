package com.spring.junitTest.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.junitTest.domain.Book;
import com.spring.junitTest.domain.BookRepository;
import com.spring.junitTest.web.dto.BookResponseDto;
import com.spring.junitTest.web.dto.BookSaveRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor		// final 필드가 있을때 무조건 주입해준다.
@Service
public class BookService {
	
	private final BookRepository bookRepository;		// final 넣게 되면 객체 생성직전에 반드시 값이 들어와야함
	
	// 1. 등록
	@Transactional(rollbackFor = RuntimeException.class)
	public BookResponseDto 책등록하기(BookSaveRequestDto dto) {
		Book bookPS = bookRepository.save(dto.toEntity());		// controller 에서 dto로 받아서 repository에 Entity형태로 넘김
		
		/**
		 * bookPS 는 영속화된 객체로서 컨트롤러까지 return 하게 되면 컨트롤러 단에서 lazy loading 발생 시
		 * (open-in-view : true - 컨트롤러단까지 세션을 유지하는 형식(지연 로딩 가능) -> 수많은 변수가 발생될 수도 있음)
		 * 영속화된 객체는 서비스단에서 빠져나가지 못하게 막아야함
		 * 그렇기 때문에 Book 구조가 아닌 BookResponseDto 구조로 return 하면서 영속화를 끊어야함 (컨트롤러는 영속화 객체가 존재하면 안됨?)
		 * */
		//Book bookPS = bookRepository.save(dto.toEntity());  
		// return bookPS;									  
		
		return new BookResponseDto().toDto(bookPS);	// 영속객체 -> 영속끊는 Dto로 변경해 리턴
															  
	}	
	
	// 2. 리스트 조회
	
	// 3. 하나 조회
	
	// 4. 삭제
	
	// 5. 수정

}
