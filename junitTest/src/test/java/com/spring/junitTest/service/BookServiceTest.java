package com.spring.junitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.spring.junitTest.domain.BookRepository;
import com.spring.junitTest.util.MailSenderStub;
import com.spring.junitTest.web.dto.BookResponseDto;
import com.spring.junitTest.web.dto.BookSaveRequestDto;

// @Service�� ���� �׽�Ʈ ������̼��� ����
@DataJpaTest
public class BookServiceTest {

	@Autowired
	BookRepository bookRepository;
	
	// ������ -> Service �� �׽�Ʈ�ϰ� ������ Repository ���̾ �Բ� �׽�Ʈ �ȴ�.
	// �ذ��ϱ� ���ؼ� Mockito�� ����Ѵ� -> BookServiceMockTest.java ����
	@Test
	public void å����ϱ�_�׽�Ʈ() {
		//given
		BookSaveRequestDto bookSaveRequestDto = new BookSaveRequestDto();
		bookSaveRequestDto.setTitle("���� ����");
		bookSaveRequestDto.setAuthor("���� ����");
		
		// stub -> MailAdapter ��¥ ��ü
		MailSenderStub mailSenderStub = new MailSenderStub();
		
		// when 
		BookServiceAddMail bookServiceAddMail = new BookServiceAddMail(bookRepository, mailSenderStub);
		BookResponseDto bookResponseDto = bookServiceAddMail.å����ϱ�(bookSaveRequestDto);
		
		// then
		assertEquals(bookSaveRequestDto.getTitle(), bookResponseDto.getTitle());
		assertEquals(bookSaveRequestDto.getAuthor(), bookResponseDto.getAuthor());
	}
}
