package com.spring.junitTest.web.dto;

import com.spring.junitTest.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookResponseDto {

	private Long id;
	private String title;
	private String author;
	
 		//�̷��� ȣ���ϸ� BookResponseDto ��ü�� ���� (new BookResponseDto ���� �������) �����ؾ���..
	public  BookResponseDto toDto(Book bookPS) {
		this.id = bookPS.getId();
		this.title = bookPS.getTitle();
		this.author = bookPS.getAuthor();
		return this;	// �ڱ��ڽ� ����
	}
	
	// static ���� ���� �ܺο��� ResponseDto.toDto �������� ȣ�� �����ϰ� �����.
//	public static BookResponseDto toDto(Book bookPS) {
//		BookResponseDto dto = new BookResponseDto();
//		dto.id = bookPS.getId();
//		dto.title = bookPS.getTitle();
//		dto.author = bookPS.getAuthor();
//		return dto;	
//	}
//	

	
	
	
}
