package com.spring.junitTest.util;

import org.springframework.stereotype.Component;

// ��¥ ��ü, ���� Mail�� �ϼ� �ȵǾ�����
@Component
public class MailSenderStub implements MailSender{

	@Override
	public boolean send() {
		return true;
	}
}
