package com.spring.junitTest.domain;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 테이블 생성
@Getter // setter 사용 금지
@NoArgsConstructor
public class BookTest {
	
	private long id;
	private String title;
	private String author;
	
	@Builder	// 자유롭게 넣고 싶은 순서,값 넣을 수 있음
	public BookTest(long id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

}
