package com.gavinwilson.livable.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gavinwilson.livable.models.EventMessage;
import com.gavinwilson.livable.repositories.EventMessageRepository;


@Service
public class EventMessageService {
	
	
	@Autowired
	private EventMessageRepository messageRepository;
	
	public EventMessage createMessage(EventMessage m) {
		return messageRepository.save(m);	}
	

	}