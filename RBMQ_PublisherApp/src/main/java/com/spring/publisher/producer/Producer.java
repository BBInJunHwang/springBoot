package com.spring.publisher.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);

	private static final String EXCHANGE_NAME = "sample.exchange";

    @Autowired
    RabbitTemplate rabbitTemplate;
    
    int i =1;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)	// 스케줄링 실시 fixedDely = 몇초에 한번씩, initialDelay = 최초작업 이전 대기 시간 => 0.5초 대기후 매1초마다 실시
    public void sendMessage(){
    	
		rabbitTemplate.convertAndSend(EXCHANGE_NAME, "sample.oingdaddy.#", i + "번째 테스트 입니다.!");	// rabbitTemplate 이용해서 메세지 전송, Object로 보내기 떄문에 객체로부터 변환된 메세지 발송
		i++;
		log.info("send end");
    }
}