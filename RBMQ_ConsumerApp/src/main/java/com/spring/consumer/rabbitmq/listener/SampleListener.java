package com.spring.consumer.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * AMPQ :  Advanced Message Queing Protocol의 약자로, 흔히 알고 있는 MQ의 오픈소스에 기반한 표준 프로토콜
 * 응용 계층 메시지 전달 표준
 * 클라이언트 미들웨어 브로커 간 데이터 교환을 위한 MQ기반 메시지 교환 프로토콜
 * 
 * 미들웨어 : 서로 다른 애플리케이션이 서로 통신하는 데 사용되는 SW  
 * 
 * */

@Component
public class SampleListener {
	private static final Logger log = LoggerFactory.getLogger(SampleListener.class);

	@RabbitListener(queues = "sample.queue")	// 해당 리스너와 queue 이름 설정시 queue 메세지 가져옴
	public void receiveMessage(final Message message) {
		log.info(message.toString());
		
		// 비지니스 로직 부분이라 생각하고 스레드 3초간 멈춤(DB 커넥션 + 데이터 처리 시간 등)
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
