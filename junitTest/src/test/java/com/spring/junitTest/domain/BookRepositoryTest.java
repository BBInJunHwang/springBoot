package com.spring.junitTest.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * 
 * �׽�Ʈ��?
 * 
 * ������ ��Ʈ ���� �� ������ ��Ʈ ��ü ���� �� ��ü ����
 * 
 * �׽�Ʈ ���� �� ������ ��Ʈ�� �ƴ� �׽�Ʈ ȯ���� �����
 * �̶� controller, service, repository ��� ���°� ���� �׽�Ʈ��� �Ѵ�.
 * 
 * �׽�Ʈ ȯ�濡���� repository ó�� �ʿ��� ȯ�游 �޸𸮿� ���� �����׽�Ʈ�� �Ѵ�.
 * 
 * */

@DataJpaTest // DB ���õ� component �� �޸𸮿� �ε� �ȴ�, Controller,service �� ����.
public class BookRepositoryTest {
	
	/**
	 * 1. å ���
	 * 2. å ��Ϻ���
	 * 3. å �ѱ� ����
	 * 4. å ����
	 * 5. å ����
	 * 
	 * */
	
	@Autowired	// �׽�Ʈ �ÿ��� @Autowired �� ���°� ���ٴ���.., ������ ���Ը��� 
	private BookRepository bookRepository;
	
	
	/**
	 * �׽�Ʈ�� ���� �غ��ؾ��ϴ� ����
	 * 
	 * 1. given(������ �غ�)
	 * 2. when (�׽�Ʈ ����)
	 * 3. then (����)
	 * 
	 * */
	
	@Test
	public void å���Test() {
		
		// controller, service �޸𸮿� ��°� �ƴϱ� ������ ���� ������(request)�� �غ��ؾ��Ѵ�.
		// 1. given (������ �غ�)
		String title = "Junit5";
		String author = "ijhwang";
		
		// Repository ������ �̹� Service���� Dto -> Entity�� ��ȯ�Ǿ ���� ���Դ� �����ϰ� Book �����Ѵ�.
		Book book = Book.builder().title(title).author(author).build();
		
		// 2. when (�׽�Ʈ ����)
		Book bookPS = bookRepository.save(book);	// client���� ���� book�� DB�� ������ �ǰ�, PK id�� �����Ǹ�, save �Լ��� DB ����� Book�� �����Ѵ�.(DB�� ����� ���� ����ȭ �� ������)
		// ps -> persistence ������ ���� �ǹ�, book �� client ���ؼ� ������������, bookPS�� DB�� ���� ����������(����ȭ �� ������) ����� ���� �ǹ�
		
		// 3. then (����)
		assertEquals(title, bookPS.getTitle());	// ��밪, ������ ������ ������ assertEquals �� �����Ѵ�.
		assertEquals(author, bookPS.getAuthor());
		
		
		// 4. ���� �� debug console ���� ������� �߸� �������� assertEquals ���� �ǹ�
		// ���� �ϳ��ϳ� ���� ������ ������.. �����Ͻ� ������ ��û ���� �����Ͽ� ���� Ȯ�� �� �� ������ assertEquals ��� �� �������� �����ϰ� �Ѿ��.
	}

}
