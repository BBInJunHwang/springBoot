package com.spring.junitTest.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.junitTest.domain.Book;
import com.spring.junitTest.domain.BookRepository;
import com.spring.junitTest.web.dto.BookResponseDto;
import com.spring.junitTest.web.dto.BookSaveRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor		// final �ʵ尡 ������ ������ �������ش�.
@Service
public class BookService {
	
	private final BookRepository bookRepository;		// final �ְ� �Ǹ� ��ü ���������� �ݵ�� ���� ���;���
	
	// 1. ���
	@Transactional(rollbackFor = RuntimeException.class)
	public BookResponseDto å����ϱ�(BookSaveRequestDto dto) {
		Book bookPS = bookRepository.save(dto.toEntity());		// controller ���� dto�� �޾Ƽ� repository�� Entity���·� �ѱ�
		
		/**
		 * bookPS �� ����ȭ�� ��ü�μ� ��Ʈ�ѷ����� return �ϰ� �Ǹ� ��Ʈ�ѷ� �ܿ��� lazy loading �߻� ��
		 * (open-in-view : true - ��Ʈ�ѷ��ܱ��� ������ �����ϴ� ����(���� �ε� ����) -> ������ ������ �߻��� ���� ����)
		 * ����ȭ�� ��ü�� ���񽺴ܿ��� ���������� ���ϰ� ���ƾ���
		 * �׷��� ������ Book ������ �ƴ� BookResponseDto ������ return �ϸ鼭 ����ȭ�� ������� (��Ʈ�ѷ��� ����ȭ ��ü�� �����ϸ� �ȵ�?)
		 * */
		//Book bookPS = bookRepository.save(dto.toEntity());  
		// return bookPS;									  
		
		return new BookResponseDto().toDto(bookPS);	// ���Ӱ�ü -> ���Ӳ��� Dto�� ������ ����
															  
	}	
	
	// 2. ����Ʈ ��ȸ
	
	// 3. �ϳ� ��ȸ
	
	// 4. ����
	
	// 5. ����

}
