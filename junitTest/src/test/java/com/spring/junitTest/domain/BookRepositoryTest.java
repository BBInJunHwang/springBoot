package com.spring.junitTest.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

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
 * 
 * junit �ȿ� �޼��尡 �������� ������ ���� ���� �� ���� ������ �ȵȴ�.
 * ex) ��� > ����Ʈ > 1����ȸ > ���� �̷� ������ �����Ǿ��� �ִٰ� �ص� ������� ���� ����ȵ�
 * ���� ������ ���ؼ��� Order() ������̼��� �ʿ��ϴ�.
 * 
 * �׽�Ʈ �޼��� �ϳ� ���� �� ����Ǹ� �����Ͱ� �ʱ�ȭ �ȴ�.
 * @DataJpaTest �ȿ� @Transactional �� @Test �޼��� �ϳ� ������ rollback? �ʱ�ȭ �����ش�.
 * 
 * @Transactional ���� �� ������ �ʱ�ȭ ��� pk (auto increment) �ǰ��ִ� ���� �ʱ�ȭ�� �ȵȴ�.
 * �׷��� å�ѱǺ���_test���� 1L �����Ͷ� �ҋ� ������ ���,����Ʈ �׽�Ʈ���� 1,2..�� �����Ͱ� ���� �����Ǿ����� auto increment ������ ���� No value present �ߴ°Ŵ�.
 * 
 * */

@DataJpaTest // DB ���õ� component �� �޸𸮿� �ε� �ȴ�, Controller,service �� ����.
public class BookRepositoryTest {
	
	@Autowired	// �׽�Ʈ �ÿ��� @Autowired �� ���°� ���ٴ���.., ������ ���Ը��� 
	private BookRepository bookRepository;
	
	/**
	 * 
	 * 1. å ���
	 * 2. å ��Ϻ���
	 * 3. å �ѱ� ����
	 * 4. å ����
	 * 5. å ����
	 * 
	 * �׽�Ʈ�� ���� �غ��ؾ��ϴ� ����
	 * 
	 * 1. given(������ �غ�)
	 * 2. when (�׽�Ʈ ����)
	 * 3. then (����)
	 * 
	 * */
	
	//@BeforeAll //  �׽�Ʈ �������� �ѹ��� ���� 
	@BeforeEach // �� �׽�Ʈ ���� ���� �ѹ��� ������ -> �޼��� �ϳ����� Ʈ������� ���δ�. (å���, å���, å�ѱ� ������ ����)
	public void �������غ�() {
		System.out.println("Before Data Ready----------------------------------");
		String title = "Junit";
		String author = "ijhwang01";
		Book book = Book.builder().title(title).author(author).build();
		bookRepository.save(book);
	} // [������ �غ� + å���]Ʈ����ǹ���  [������ �غ� + å��Ϻ���]Ʈ����ǹ���  [������ �غ� + å�ѱǺ���]Ʈ����ǹ���
	
	
	@Order(1)
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
		
		
		// @Test �� Ʈ����� ���� �� ������ϰ� ��������
	}
	
	@Order(2)
	@Test	
	public void å��Ϻ���_test() {
		// given
		String title = "Junit";
		String author = "ijhwang01";
		// when
		List<Book> booksPS = bookRepository.findAll();
		
		// then
		assertEquals(title, booksPS.get(0).getTitle());
		assertEquals(author, booksPS.get(0).getAuthor());
	}
	
	@Sql("classpath:db/tableInit.sql") // classpath -> src/main/resources ����, �ش� �޼��� �������� sql ���� ����, ���� junit ����� pk ���� ����
	@Order(3)
	@Test
	public void å�ѱǺ���_test() {
		// given
		String title = "Junit";
		String author = "ijhwang01";
		
		// when
		Book bookPS = bookRepository.findById(1L).get();
		
		// then
		assertEquals(title, bookPS.getTitle());
		assertEquals(author, bookPS.getAuthor());
	}

	@Sql("classpath:db/tableInit.sql") 
	@Order(4)
	@Test
	public void å����_test() {
		//given
		Long id = 1L;
		
		//when
		bookRepository.deleteById(id);
		
		//then
		assertFalse(bookRepository.findById(id).isPresent()); // false �̸� ���� -> isPresent�� Optional �ȿ� ������ ������ true ��ȯ ������ false ��ȯ 
	}
}
