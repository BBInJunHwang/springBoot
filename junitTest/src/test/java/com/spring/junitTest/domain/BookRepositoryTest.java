package com.spring.junitTest.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;


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
 * Junit Test
 * �޼��� ����   -> �޼��� ����  -> RollBack (Junit �ƴѰ�� default �� commit ��, runtime Exception �� rollback )
 * (Ʈ����� ����) (Ʈ����� ����)
 * 
 * 
 * */

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)	// @Order �ƶ��� ���� ������ �׽�Ʈ ���� Ȱ��ȭ
@DataJpaTest // DB ���õ� component �� �޸𸮿� �ε� �ȴ�, Controller,service �� ����.
public class BookRepositoryTest {
	
	@Autowired	// �׽�Ʈ �ÿ��� @Autowired �� ���°� ���ٴ���.., ������ ���Ը��� 
	private BookRepository bookRepository;
	
	/**
	 * 
	 * ����
	 * 1. ���
	 * 2. ����Ʈ ��ȸ
	 * 3. �ϳ� ��ȸ
	 * 4. ����
	 * 5. ����
	 * 
	 * �׽�Ʈ�� ���� �غ��ؾ��ϴ� ����
	 * 1. given(������ �غ�) -> controller, service �޸𸮿� ��°� �ƴϱ� ������ ���� ������(request)�� �غ��ؾ��Ѵ�.
	 * 2. when (�׽�Ʈ ����)
	 * 3. then (����)
	 * 
	 * */
	
	//@BeforeAll //  �׽�Ʈ �������� �ѹ��� ���� 
	@BeforeEach // �� �׽�Ʈ ���� ���� �ѹ��� ������ -> �޼��� �ϳ����� Ʈ������� ���δ�. (å���, å���, å�ѱ�... ������ ����)
	public void �������غ�() {
		System.out.println("Before Data Ready----------------------------------");
		String title = "junit5";
		String author = "ijhwang01";
		Book book = Book.builder().title(title).author(author).build();
		bookRepository.save(book);
	} // [������ �غ� + å���]Ʈ����ǹ���  [������ �غ� + å��Ϻ���]Ʈ����ǹ���  [������ �غ� + å�ѱǺ���]Ʈ����ǹ���
	
	
	@Test
	@Order(0)
	public void å���Test() {
		
		// 1. given (������ �غ�)
		String title = "junit";
		String author = "ijhwang";
		
		// Repository ������ �̹� Service���� Dto -> Entity�� ��ȯ�Ǿ ���� ���Դ� �����ϰ� Book �����Ѵ�.
		Book book = Book.builder().title(title).author(author).build();
		
		// 2. when (�׽�Ʈ ����)
		Book bookPS = bookRepository.save(book);	// client���� ���� book�� DB�� ������ �ǰ�, PK id�� �����Ǹ�, save �Լ��� DB ����� Book�� �����Ѵ�.(DB�� ����� ���� ����ȭ �� ������)
		// ps -> persistence ������ ���� �ǹ�, book �� client ���ؼ� ������������, bookPS�� DB�� ���� ����������(����ȭ �� ������) ����� ���� �ǹ�
		
		// 3. then (����) ���� �� debug console ���� ������� �߸� �������� assertEquals ���� �ǹ�
		//				 ���� �ϳ��ϳ� ���� ������ ������.. �����Ͻ� ������ ��û ���� �����Ͽ� ���� Ȯ�� �� �� ������ assertEquals ��� �� �������� �����ϰ� �Ѿ��.
		assertEquals(title, bookPS.getTitle());	// ��밪, ������ ������ ������ assertEquals �� �����Ѵ�.
		assertEquals(author, bookPS.getAuthor());
		
	}// @Test �� Ʈ����� ���� �� ������ϰ� ��������
	
	
	@Test	
	@Order(1)
	public void å��Ϻ���_test() {
		// given
		String title = "junit5";
		String author = "ijhwang01";
		// when
		List<Book> booksPS = bookRepository.findAll();
		
		// then
		assertEquals(3L, booksPS.get(0).getId());			// @BeforeEach ������ + ��� ������ �ؼ� 2�� �� �� rollback �ص� auto increment�� �����Ǳ� ������ 3�� ���� insert��
		assertEquals(title, booksPS.get(0).getTitle());
		assertEquals(author, booksPS.get(0).getAuthor());
	}
	
	
	
	
	// table drop �ϴ� ����
	// 1. �׽�Ʈ ���, ��� ����~����~Ʈ����� ���� (��� �ϱ� @BeforeEach ������ + �׽�Ʈ ��� ������ �ؼ� 2��   +  ��� ���� @BeforeEach ������ �ؼ� �� 3���� �̹� ����, �ѹ� ó���� �����Ͱ� �������� ������ auto_increment���� �����ǹ�����)
	// 2. �׽�Ʈ �ѱ� ���� 	���� (�ѱ� ���� @BeforeEach ������ ��� �� 4�� �����ͷ� �� -> auto increment�� ������ó�� Ʈ����� �ѹ�� ������ ���ƿ��� ����)
	@Sql("classpath:db/tableInit.sql") // classpath -> src/main/resources ����, �ش� �޼��� �������� sql ���� ����, ���� junit ����� pk ���� ����
	@Test
	@Order(2)
	public void å�ѱǺ���_test() {
		// given
		String title = "junit5";
		String author = "ijhwang01";
		
		// when
		//Book bookPS = bookRepository.findById(4L).get();
		Book bookPS = bookRepository.findById(1L).get();
		
		// then
		assertEquals(title, bookPS.getTitle());
		assertEquals(author, bookPS.getAuthor());
	}

	@Sql("classpath:db/tableInit.sql") 
	@Test
	@Order(3)
	public void å����_test() {
		//given
		//Long id = 5L;	
		Long id = 1L;
		
		bookRepository.deleteById(id);
		
		//then
		assertFalse(bookRepository.findById(id).isPresent()); // false �̸� ���� -> isPresent�� Optional �ȿ� ������ ������ true ��ȯ ������ false ��ȯ 
	}
	
	
	
	// commit �̶� Ʈ����ǵ��� �޸𸮿� �߻��� �����͸� HDD �� �ű�
	// rollback �̶� Ʈ����ǵ��� �޸� �����͸� ���� 
	
	// Junit update flow
	//  1. @BeforeEach (Ʈ����� ����) 1�� save ����	  		-> ���� commit �� �ƴϱ� ������ �޸𸮿� ����(insert ������)  
	//  2. @Sql update �޼ҵ� ȣ�� ������ table drop�� �Ͼ 	-> �޸𸮰� �ƴ� HDD�� �ִ� ���̺��� �����°���(auto_increment �ʱ�ȭ), �޸𸮿��� ������ �״�� ����, ���̺� drop���� �������
	//  3. update �׽�Ʈ (Ʈ����� ����) 1�� update     		-> Ʈ������� �����Ǳ� ������ �޸𸮿� �����ϴ� �����͸� update
	//  4. �޼ҵ� ����										-> Ʈ����� ����, Rollback -> �޸� �ѹ�
	@Sql("classpath:db/tableInit.sql") 
	@Test
	@Order(4)
	public void å����_test() {
		// given
		//Long id = 6L;
		Long id = 1L;
		String title = "junit5 ����";
		String author ="ijhwang01 ����";
		Book book = new Book(id,title,author);
		
		//when
		// ������� dirty check�� �ؾ�������, before ������ insert �� �̸� �ǹ����� ������ �Ұ��� , save �� 1L �� �ֱ⶧���� merge ����
		Book bookPS = bookRepository.save(book);
		
//		bookRepository.findAll().stream()
//		.forEach(b -> {
//			System.out.println("-----------------------");
//			System.out.println(b.getId());
//			System.out.println(b.getTitle());
//			System.out.println(b.getAuthor());
//			System.out.println("-----------------------");
//			});

		//then
		assertEquals(id, bookPS.getId());
		assertEquals(title, bookPS.getTitle());
		assertEquals(author, bookPS.getAuthor());
	}
	
	
}
