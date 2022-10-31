package com.spring.junitTest.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.junitTest.domain.Book;
import com.spring.junitTest.domain.BookRepository;
import com.spring.junitTest.util.MailSender;
import com.spring.junitTest.web.dto.BookResponseDto;
import com.spring.junitTest.web.dto.BookSaveRequestDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor		// final 필드가 있을때 무조건 주입해준다.
@Service
public class BookServiceAddMail {
	
	private final BookRepository bookRepository;		
	
	/**
	 * mail send 예시
	 * */
	private final MailSender mailSender;
	
	// 1. 등록
	@Transactional(rollbackFor = RuntimeException.class)
	public BookResponseDto 책등록하기(BookSaveRequestDto dto) {
		Book bookPS = bookRepository.save(dto.toEntity());		
		
		if(bookPS != null) {
			if(!mailSender.send()) {
				throw new RuntimeException("메일이 전송되지 않았습니다.");
			}
		}
											  
		
		return new BookResponseDto().toDto(bookPS);	// 영속객체 -> 영속끊는 Dto로 변경해 리턴
															  
	}	
	
	// 2. 리스트 조회
	public List<BookResponseDto> 책목록보기(){			
		return bookRepository.findAll()
				.stream() 
				.map(new BookResponseDto()::toDto) 
				.collect(Collectors.toList());	
	}
	
	
	// 3. 하나 조회
	public BookResponseDto 책한건보기(Long id) {
		
		Optional<Book> bookPS = bookRepository.findById(id);
		
		if(bookPS.isPresent()) {
			return new BookResponseDto().toDto(bookPS.get());
		}else {
			throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
		}
	}
	
	// 4. 삭제
	
	@Transactional(rollbackFor = RuntimeException.class)
	public void 책삭제하기(Long id) {
		bookRepository.deleteById(id);	
	}
	
	// 5. 수정
	@Transactional(rollbackFor = RuntimeException.class)
	public void 책수정하기(Long id, BookSaveRequestDto dto) {
		Optional<Book> bookOP = bookRepository.findById(id);
		if(bookOP.isPresent()) {
			Book bookPS = bookOP.get();
			bookPS.update(dto.getTitle(), dto.getAuthor());	
		}else {
			throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");	
		}
	}
}
