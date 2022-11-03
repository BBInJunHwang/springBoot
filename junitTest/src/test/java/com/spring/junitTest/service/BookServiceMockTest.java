package com.spring.junitTest.service;

// assertJ ���
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.spring.junitTest.domain.Book;
import com.spring.junitTest.domain.BookRepository;
import com.spring.junitTest.util.MailSender;
import com.spring.junitTest.web.dto.request.book.BookSaveRequestDto;
import com.spring.junitTest.web.dto.response.book.BookResponseDto;



@ExtendWith(MockitoExtension.class)
public class BookServiceMockTest {

	//@Autowired ��� �Ұ� -> Service�� �ø��� ������̼��� BookServiceMockTest ���� ��ð� ���� �ʱ� ���� 
	@InjectMocks	// �ش� ���񽺰� �޸𸮿� �㶧 @Mock�� ���� ��¥ ��ü���� �����Ѵ�.
	private BookServiceAddMail bookServiceAddMail;
	
	
	/**
	 * @Mock �̿��ؼ� ��¥ȯ���� ����.
	 * �������̽��� �̿��� �͸�Ŭ����(�������̽��� new �ϸ� -> �������̽��� new �Ұ����ؼ� ����ü ����)
	 * */
	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private MailSender mailSender;

	
	@Test
	public void å����ϱ�_�׽�Ʈ() {
		//given
		BookSaveRequestDto bookSaveRequestDto = new BookSaveRequestDto();
		bookSaveRequestDto.setTitle("���� ����");
		bookSaveRequestDto.setAuthor("���� ����");

		// bookRepository ���� DB�� �����͸� ��ȸ���� �ʱ⶧���� ã�� bookPS�� null�ϰŰ� �ᱹ NPE�� �߻��Ѵ�.
		// ���� ���� (stub ����) -> �ൿ���ǰ� �ʿ� ������ NPE �߻�
		when(bookRepository.save(any())).thenReturn(bookSaveRequestDto.toEntity());	// save�� ����ɶ� any(�ƹ��ų� ��������) thenReturn������ �����Ѵ�.
		when(mailSender.send()).thenReturn(true);	// send�� �����Ҷ� true��� �����Ѵ�.
		
		
		// when 
		BookResponseDto bookResponseDto = bookServiceAddMail.å����ϱ�(bookSaveRequestDto);
		
		// then
//		assertEquals(bookSaveRequestDto.getTitle(), bookResponseDto.getTitle());
//		assertEquals(bookSaveRequestDto.getAuthor(), bookResponseDto.getAuthor());
		
		//https://assertj.github.io/doc/ assertJ ����
		// then assertJ lib ��� 
		assertThat(bookResponseDto.getTitle()).isEqualTo(bookSaveRequestDto.getTitle());
		assertThat(bookResponseDto.getAuthor()).isEqualTo(bookSaveRequestDto.getAuthor());
	}
	
	@Test
	public void å��Ϻ���_�׽�Ʈ() {
		// given
		List<Book> books = new ArrayList<>();
		books.add(new Book(1L,"����1","����1"));
		books.add(new Book(2L,"����2","����2"));
		books.add(new Book(3L,"����3","����3"));
		
		// stub
		when(bookRepository.findAll()).thenReturn(books);
		
		// when
		List<BookResponseDto> list = bookServiceAddMail.å��Ϻ���();
		
		// then
		assertThat(list.get(0).getTitle()).isEqualTo("����1");
		assertThat(list.get(0).getAuthor()).isEqualTo("����1");
		assertThat(list.get(1).getTitle()).isEqualTo("����2");
		assertThat(list.get(1).getAuthor()).isEqualTo("����2");
		assertThat(list.get(2).getTitle()).isEqualTo("����3");
		assertThat(list.get(2).getAuthor()).isEqualTo("����3");
	}
	
	@Test
	public void å�ѰǺ���_test() {
		// given
		Long id = 1L;
		Book book = new Book(1L,"����1","����1");
		
		// findById �� optional�� ��ȯ�ϱ� ������ ���������
		Optional<Book> bookOP = Optional.of(book);
		
		// stub
		when(bookRepository.findById(id)).thenReturn(bookOP);
		
		
		// when
		BookResponseDto bookResponseDto = bookServiceAddMail.å�ѰǺ���(id);
		
		// then
		assertThat(bookResponseDto.getTitle()).isEqualTo(book.getTitle());
		assertThat(bookResponseDto.getAuthor()).isEqualTo(book.getAuthor());
	}
	
	@Test
	public void å�����ϱ�_test() {
		// given
		Long id = 1L;
		BookSaveRequestDto bookSaveRequestDto = new BookSaveRequestDto();
		bookSaveRequestDto.setTitle("����1 ����");
		bookSaveRequestDto.setAuthor("����1 ����");
		
		// stub
		Book book = new Book(1L,"����1","����1");
		Optional<Book> bookOP = Optional.of(book);
		when(bookRepository.findById(id)).thenReturn(bookOP);
		
		// when
		BookResponseDto bookResponseDto = bookServiceAddMail.å�����ϱ�(id, bookSaveRequestDto);
		
		// then
		assertThat(bookResponseDto.getTitle()).isEqualTo(bookSaveRequestDto.getTitle());
		assertThat(bookResponseDto.getAuthor()).isEqualTo(bookSaveRequestDto.getAuthor());
	}
}
