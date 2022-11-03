package com.spring.junitTest.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.junitTest.domain.Book;
import com.spring.junitTest.domain.BookRepository;
import com.spring.junitTest.util.MailSender;
import com.spring.junitTest.web.dto.request.book.BookSaveRequestDto;
import com.spring.junitTest.web.dto.response.book.BookResponseDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor		// final �ʵ尡 ������ ������ �������ش�.
@Service
public class BookServiceAddMail {
	
	private final BookRepository bookRepository;		
	
	/**
	 * mail send ����
	 * */
	private final MailSender mailSender;
	
	// 1. ���
	@Transactional(rollbackFor = RuntimeException.class)
	public BookResponseDto å����ϱ�(BookSaveRequestDto dto) {
		Book bookPS = bookRepository.save(dto.toEntity());		
		
		if(bookPS != null) {
			if(!mailSender.send()) {
				throw new RuntimeException("������ ���۵��� �ʾҽ��ϴ�.");
			}
		}
											  
		
		return bookPS.toDto();	// ���Ӱ�ü -> ���Ӳ��� Dto�� ������ ����
															  
	}	
	
	// 2. ����Ʈ ��ȸ
	public List<BookResponseDto> å��Ϻ���(){			
		return bookRepository.findAll()
				.stream() 
				//.map((bookPS) -> new BookResponseDto().toDto(bookPS))
				//.map((bookPS) -> bookPS.toDto())
				.map(Book::toDto) // �޼��� ���� ��� ::	List stream �� �ϳ��� ������ Book type���� toDto �����Ѵ�.
				.collect(Collectors.toList());	
		
		//  �޼��� ������� :: �� ���ԵǸ� �Ʒ��� ���� �ϳ��� dto ���� -> ���� �ϳ�, ������ dto�� ����ϱ� ������ new �� list �����ŭ�Ȱ� �ƴ϶� new�� 1���� �ǰ�
		//  toDto �޼��常 list �����ŭ ����Ǵ°Ŵ�. -> �ᱹ �ѹ� ���� �ް� toDto�� N�� ����Ǵ°Ŵ�. -> ó�� ���޹��� �� N�� ����Ǿ �ϳ����� ����ȴ�.
//		BookResponseDto dto = new BookResponseDto();
//		.map(dto::toDto)

		
		
//				.stream() 
//				.map(new BookResponseDto()::toDto) 
//				.collect(Collectors.toList());	
	}
	
	
	// 3. �ϳ� ��ȸ
	public BookResponseDto å�ѰǺ���(Long id) {
		
		Optional<Book> bookOP = bookRepository.findById(id);
		
		if(bookOP.isPresent()) {
			Book BookPS = bookOP.get();
			return BookPS.toDto();
		}else {
			throw new RuntimeException("�ش� ���̵� ã�� �� �����ϴ�.");
		}
	}
	
	// 4. ����
	
	@Transactional(rollbackFor = RuntimeException.class)
	public void å�����ϱ�(Long id) {
		bookRepository.deleteById(id);	
	}
	
	// 5. ����
	@Transactional(rollbackFor = RuntimeException.class)
	public BookResponseDto å�����ϱ�(Long id, BookSaveRequestDto dto) {
		Optional<Book> bookOP = bookRepository.findById(id);
		if(bookOP.isPresent()) {
			Book bookPS = bookOP.get();
			bookPS.update(dto.getTitle(), dto.getAuthor());
			
			return bookPS.toDto();
		}else {
			throw new RuntimeException("�ش� ���̵� ã�� �� �����ϴ�.");	
		}
	}
	
//	@Transactional(rollbackFor = RuntimeException.class)
//	public void å�����ϱ�(Long id, BookSaveRequestDto dto) {
//		Optional<Book> bookOP = bookRepository.findById(id);
//		if(bookOP.isPresent()) {
//			Book bookPS = bookOP.get();
//			bookPS.update(dto.getTitle(), dto.getAuthor());
//		}else {
//			throw new RuntimeException("�ش� ���̵� ã�� �� �����ϴ�.");	
//		}
//	}
}
