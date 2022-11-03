package com.spring.junitTest.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.junitTest.domain.Book;
import com.spring.junitTest.domain.BookRepository;
import com.spring.junitTest.web.dto.request.book.BookSaveRequestDto;
import com.spring.junitTest.web.dto.response.book.BookResponseDto;

import lombok.RequiredArgsConstructor;

/**
 * ������ ����											(Transaction Start)
 * client -> filter -> Dispatcher Servlet -> Controller -> Service -> Repository -> Persistent Context -> DB
 * Req   (json data)								 (reqDto)	   (Entity)		(is Data exist?)		(save)
 * Res   (json data)								 (resDto)	   (Entity)		(Persistent Object)		(save)
 * 													(Transaction End) -> db write x
 * 									(Session close)	-> select o		
 * 
 * ���� �ܿ��� Ʈ������� ���� (DB save,update �� �Ұ���)
 * ��Ʈ�ѷ� �ܿ��� ���� ���� (select ����)
 * ���� ��Ʈ�ѷ����� ���񽺴ܿ��� ���Ӱ�ü�� �ްԵǸ�.. ��Ʈ�ѷ����� �������� �ִ� �����͸� ��ȸ�ϰԵǸ� ex) book-user �������谡 �־ .getUser �� �ϰ� �Ǹ�
 * Lazy-loading �� �Ͼ�� �Ǹ鼭 �ٽ� DB���� user �����͸� select �ϰԵȴ�.
 * 
 * ��Ʈ�ѷ��� client�� �����ϰԵɶ� MessageConverter �ߵ��ϰ� �Ǵµ� �̶� �ڵ����� getter �޼ҵ带 �� ȣ�� �� json���� �����ؼ� �����Ѵ�.
 * (��Ʈ�ѷ� �������� ���Ӱ�ü ����� ���� �ʴ��� ���� �� �ڵ����� �ߵ�) 
 * �̶� FK �������� �����͸� ���� ã�Եȴ�
 * ex) book �����͸� �޶��ߴµ� ������ user �����͸� ���� ã�Ƽ� �����Ѵ�. (��ȿ����?)
 * 
 * ����� bookPS ó�� ���Ӱ�ü�� �����ϰ� �Ǹ� ��Ʈ�ѷ����� lazy-loading�� �Ͼ�� �ֱ� ������
 * ���� �ܿ��� PS��ü -> DTO ��ȯ�ϸ� �߻��� ������ �� �ִ�.
 * 
 * 
 * */



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
		
		return bookPS.toDto();	// ���Ӱ�ü -> ���Ӳ��� Dto�� ������ ����
															  
	}	
	
	// 2. ����Ʈ ��ȸ
	public List<BookResponseDto> å��Ϻ���(){			
		return bookRepository.findAll()
				.stream() // List�� stream �ϸ� type���� ������ �ǰ� ���� ��ü�� �Ȱǵ帮�� ������
				.map(Book::toDto) // .map�� �ϸ� �ϳ��� ��ȸ�ϸ鼭 '�ٸ�Ÿ��' ���� �����ؼ�(�״�ε� ����)�������ش�. toDto�� static �̸� new ��ü ���� �ٷ� ��밡��
				.collect(Collectors.toList());	// List�� ��ȯ���ش�.
		
//		return bookRepository.findAll()
//		.stream() 
//		//.map((bookPS) -> new BookResponseDto.toDto(bookPS))	// map ��� �����ϰ� �ٲܼ������� 
//		//.map(new BookResponseDto()::toDto) 					// �ڹ�1.8���� ���� �޼��� ���� ������ :: , ������ ���� �� �ν��Ͻ�, ����ƽ �޼��� ��������
//		.collect(Collectors.toList());	
		
		
		// stream ����
		// ex) ����Ʈ�� A A A B B �̷� stream���� ���� �� Filter ���ؼ� A�� �ɷ����� map �� �̿��� AŸ�� stream���� �ٽú��� �� 
		// 	   collect�� ����(��ȯ.. ������������ ����?) 
	}
	
	
	// 3. �ϳ� ��ȸ
	public BookResponseDto å�ѰǺ���(Long id) {
		
		Optional<Book> bookOP = bookRepository.findById(id);
		
		if(bookOP.isPresent()) {
			Book bookPS = bookOP.get();
			return bookPS.toDto();
		}else {
			throw new RuntimeException("�ش� ���̵� ã�� �� �����ϴ�.");
		}
	}
	
	// 4. ����
	
	@Transactional(rollbackFor = RuntimeException.class)
	public void å�����ϱ�(Long id) {
		bookRepository.deleteById(id);	// delete from book where id = ??? ȣ��� ���� ���ٰ� �ص� DB ������ �ȳ�����
										// ??? �� ���ٰ� �ؼ� ������ �߻������� ������
		 								//	���� id ���� null �ΰ�� IllegalArgumentException ������.
	}
	
	// 5. ����
	@Transactional(rollbackFor = RuntimeException.class)
	public void å�����ϱ�(Long id, BookSaveRequestDto dto) {
		Optional<Book> bookOP = bookRepository.findById(id);
		if(bookOP.isPresent()) {
			Book bookPS = bookOP.get();
			bookPS.update(dto.getTitle(), dto.getAuthor());	// ���Ӱ�ü ���� �����Ѵ�. �޼ҵ� ���� �� dirty check���� �ڵ����� DB flush �Ǹ鼭 update commit �ȴ�.
		}else {
			throw new RuntimeException("�ش� ���̵� ã�� �� �����ϴ�.");	// Ʈ����� �ɸ� �޼���� ��Ʈ�ѷ��� ������ �ʿ䰡 ����. throw ���� ó�� ���� �ʿ�
		}
	}

}
