package com.spring.junitTest.domain;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // ���̺� ����
@Getter // setter ��� ����
@NoArgsConstructor
public class BookTest {
	
	private long id;
	private String title;
	private String author;
	
	@Builder	// �����Ӱ� �ְ� ���� ����,�� ���� �� ����
	public BookTest(long id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

}
