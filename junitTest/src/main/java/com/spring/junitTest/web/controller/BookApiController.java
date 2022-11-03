package com.spring.junitTest.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.junitTest.service.BookServiceAddMail;
import com.spring.junitTest.web.dto.request.book.BookSaveRequestDto;
import com.spring.junitTest.web.dto.response.book.BookResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookApiController {

	private final BookServiceAddMail bookServiceAddMail;	// final field�� ��������(����, has ����) -> �ش� ��Ʈ�ѷ��� new �ɶ�  �ݵ�� �ʱ�ȭ�� �Ǿ���Ѵ�.
															// final �̸� �ݵ�� �ʱ�ȭ �Ǿ���� -> @RequiredArgsConstructor ��� 
	
	/**
	 * ������ �⺻ parsing ����
	 * key=value&key=value
	 * 
	 * json Ÿ��
	 * {"key" : "value"} �ޱ� ���ؼ� @RequestBody �ʿ� 
	 * */
	
	//1. å���
	@PostMapping("/api/v1/book")
	public ResponseEntity<?> saveBook(@RequestBody BookSaveRequestDto bookSaveRequestDto) {
		BookResponseDto bookResponseDto = bookServiceAddMail.å����ϱ�(bookSaveRequestDto);
		return new ResponseEntity<>(bookResponseDto, HttpStatus.CREATED); // 201 insert
	}
	
	//2. å����Ʈ
	public ResponseEntity<?> getBookList() {
		return null;
		
	}
	
	//3. å �ѰǺ���
	public ResponseEntity<?> getBookOne() {
		return null;
		
	}
	
	//4. å �����ϱ�
	public ResponseEntity<?> deleteBook() {
		return null;
		
	}
	
	//5. å �����ϱ�
	public ResponseEntity<?> updateBook() {
		return null;
		
	}
}
