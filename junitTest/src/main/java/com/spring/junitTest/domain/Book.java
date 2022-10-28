package com.spring.junitTest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // ���̺� ����
@Getter // setter ��� ����
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 1�� ����
	private long id;
	
	@Column(length = 50, nullable = false)
	private String title;
	
	@Column(length = 20, nullable = false)
	private String author;
	
	@Builder
	public Book(long id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
	}
}
