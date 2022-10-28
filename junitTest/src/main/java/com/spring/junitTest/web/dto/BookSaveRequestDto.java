package com.spring.junitTest.web.dto;

import com.spring.junitTest.domain.Book;

import lombok.Setter;

/**
 * ����� Entity�� ����ϸ� �ȵȴ�.
 * DB ó�� �� dto �� ��ȯ�ؼ� ó���ؾ��Ѵ�.
 * 
 * Controller ���� Request ���� �� RequestDto�� ��ȯ�� Service�� ���� �ѱ��.
 * Service�� RequestDto�� ���� �� Entity�� ��ȯ�� Repository�� �ѱ��.
 * 
 * */

@Setter // Controller ���� setter �� ȣ��Ǹ鼭 Dto�� ���� ä����
public class BookSaveRequestDto {

	private String title;
	private String author;
	
	
	public Book toEntity() {
		return Book.builder()
				.title(title)
				.author(author)
				.build();
	}
	
}
