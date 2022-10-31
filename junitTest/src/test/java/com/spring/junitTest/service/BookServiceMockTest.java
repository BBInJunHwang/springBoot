package com.spring.junitTest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.spring.junitTest.domain.BookRepository;
import com.spring.junitTest.util.MailSender;
import com.spring.junitTest.web.dto.BookResponseDto;
import com.spring.junitTest.web.dto.BookSaveRequestDto;
import static org.assertj.core.api.Assertions.*;	// assertJ ���



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
		
		// then assertJ lib ��� 
		assertThat(bookSaveRequestDto.getTitle()).isEqualTo(bookResponseDto.getTitle());
		assertThat(bookSaveRequestDto.getAuthor()).isEqualTo(bookResponseDto.getAuthor());
		
	}
	
	
}
