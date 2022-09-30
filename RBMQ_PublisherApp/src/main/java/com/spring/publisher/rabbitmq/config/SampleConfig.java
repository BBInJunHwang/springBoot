package com.spring.publisher.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;


/**
 * RabbitMQ -> 메세지 큐
 * AMQP 프로토콜 사용, 많은 메세지 전달 or 요청 처리 길때 다른 API에게 위임 후 빠른 응답 실시
 * 해당 애플리케이션은 직접 DB 커넥션 필요없이 Queue에 요청 데이터만 넣고 응답 실시
 * 애플리케이션간 결합도 낮춤
 * 
 *  Producer -> 메세지 생성, 발송 주체
 *  Consumer -> Queue에 접근 후 메세지 가져옴
 *  
 *  같은설정 + 같은이름 queue 생성시 기존 queue에 연결, 다른설정 + 같은 이름 queue 생성 시 에러
 *  
 *  Exchange -> Producer 통해 전달받은 메세지를 어떤 queue에 발송할지 결정하는 객체, 일종의 라우터 개념
 *  Binding  -> Exchange에게 메세지 라우팅할 규칙 지정
 *  
 *  Exchange 타입
 *  direct - rounting key가 정확히 일치하는 queue에 메세지 전송
 *  topic  - routing key 패턴이 일치하는 queue에 메세지 전송 
 *  headers- key-value로 이뤄진 header값 기준으로 일치하는 queue에 Multicast 메세지 전송
 *  fanout - 해당 exchange에 등록된 모든 queue에 전송
 *  
 *  하나의 queue에 여러개 routing key 등록 가능
 *  
 *  Default Exchange - direct 타입이며, RabbitMQ에 생성되는 모든 queue가 자동으로 binding 됨, 각 queue이름이 routing key가 됨
 *  
 *  topic a.b.c 일떄 
 *  queue1 - *.b.*
 *  queue2 - *.*.c
 *  
 *  a.b.c 일때 q1,q2 모두 전달
 *  a.b.d 일때 q1 에게만 전달
 * 
 * 
 *  RabbitMQ 종료 시 queue 데이터 모두 날아감
 *  만약 보존시 
 *  Queue 생성시 Durable 옵션 true
 *  Producer 발송시 PERSISTENT_TEXT_PLAIN  옵션 필요
 *  
 *  하나의 Queue에 여러 consumer 존재시 round-robin 방식 기본 
 *  만약 한쪽에서 처리시간 짧고, 한쪽은 길때 하나의 consumer만 일하는 경우가 있음 
 *  이때 prefetch count =1 로 설정 -> 메세지 처리 ack 전에 새로운 메세지 받지않음, 분산가능  
 *   
 * 
 * */


public class SampleConfig {
	private static final String EXCHANGE_NAME = "sample.exchange";
	private static final String QUEUE_NAME = "sample.queue";
	private static final String ROUTING_KEY = "sample.oingdaddy.#";

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);		// exchange 설정
	}

	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME);		// queue 이름 등록
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);		// exchange 가 queue에게 메세지 전달위한 룰, routeKey 설정필요 
	}

	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) { // RabbitTemplate  받은 메시지 처리를 위한 messageConverter를 설정
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		return rabbitTemplate;
	}
}
