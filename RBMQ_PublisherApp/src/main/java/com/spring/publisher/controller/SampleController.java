package com.spring.publisher.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SampleController {
	private static final Logger log = LoggerFactory.getLogger(SampleController.class);

	
	private static final String EXCHANGE_NAME = "sample.exchange";
	@Autowired
	RabbitTemplate rabbitTemplate;

	@GetMapping("/sample/queue")
	public String samplePublish() {
		rabbitTemplate.convertAndSend(EXCHANGE_NAME, "sample.oingdaddy.#", "RabbitMQ + Springboot = Success!");
		log.info("publish success");
		return "message sending!";
	}
}
