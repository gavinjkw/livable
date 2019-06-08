package com.gavinwilson.livable.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gavinwilson.livable.models.Attend;
import com.gavinwilson.livable.models.Event;
import com.gavinwilson.livable.models.User;
import com.gavinwilson.livable.repositories.AttendRepository;
import com.gavinwilson.livable.repositories.EventRepository;
import com.gavinwilson.livable.repositories.UserRepository;

@Service
public class AttendService {

	@Autowired
	private AttendRepository attendRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	public Attend createAttend(Attend attend ) {
		return attendRepository.save(attend);
	}
	
	public Attend createAttendManual(User user, Event event ) {
		Attend attend = new Attend();
		attend.setUser(user);
		attend.setEvent(event);
		
		return attendRepository.save(attend);
	}
	
	
	public List<Attend> getAttendByUserId(Long userId){
		return attendRepository.getAttendByUserId(userId);
	}
	
	
	public void deleteAttend(Long user_id, Long event_id){
		  System.out.println("THIS IS WOKRING");
		  
		  Optional<User> optionalUser = userRepository.findById(user_id);
		  Optional<Event> optionalEvent = eventRepository.findById(event_id);

	      User newUser = optionalUser.get();

	      Event newEvent  = optionalEvent.get();
	      
	      Attend attend = attendRepository.findByUserAndEvent(newUser, newEvent);

	      attendRepository.delete(attend);        	
	}
	
	
}
