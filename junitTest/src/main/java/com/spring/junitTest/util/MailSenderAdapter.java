package com.spring.junitTest.util;

// ���Ŀ� Mail Ŭ������ �ϼ��Ǹ� �ڵ带 �ϼ��ϸ��
public class MailSenderAdapter implements MailSender{
	
//	private Mail mail;
//
//	public MailSenderAdapter(Mail mail) {
//		this.mail = mail;
//	}


	@Override
	public boolean send() {
		//return mail.sendMail();
		return true;
	}

}