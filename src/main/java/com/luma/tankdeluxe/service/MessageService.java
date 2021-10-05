package com.luma.tankdeluxe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	@Autowired
	private SimpMessagingTemplate messageBroker;
	
	public void sendMessage(String topic, Object payload) {
		this.messageBroker.convertAndSend("/topic/" + topic, payload);
	}
	
}
