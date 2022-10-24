package com.spring.junitTest.domain;

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
	
	
	@Test
	public void å���Test() {
		System.out.println("å ��� Test ����");
	}

}
