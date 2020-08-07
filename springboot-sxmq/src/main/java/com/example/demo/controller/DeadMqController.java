package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeadMqController {
	
	@Autowired
	public RabbitTemplate rabbitTemplate;
	
	@RequestMapping("/sendDeadQueue")
	public String sendDeadQueue(@RequestParam("msgData") String msgData) {
		Map<String, Object> map=new HashMap<>();
		String msgId=String.valueOf(UUID.randomUUID());
		String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss"));
		map.put("msgId", msgId);
		map.put("msgData", msgData);
		map.put("sendTime", sendTime);
		rabbitTemplate.convertAndSend("TestDeadExchange", "TestDeadRouting", map);
		return "ok";

	}

}
