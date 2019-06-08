package com.gavinwilson.livable.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gavinwilson.livable.models.HouseMessage;
import com.gavinwilson.livable.repositories.HouseMessageRepository;

@Service
public class HouseMessageService {
	
	
	@Autowired
	private HouseMessageRepository messageRepository;
	
	public HouseMessage createMessage(HouseMessage m) {
		return messageRepository.save(m);	}
	

	}
