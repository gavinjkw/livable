package com.gavinwilson.livable.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gavinwilson.livable.models.Event;
import com.gavinwilson.livable.models.House;
import com.gavinwilson.livable.models.User;
import com.gavinwilson.livable.repositories.EventRepository;


@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	public Event createEvent(Event e) {
		return eventRepository.save(e);	}
	
	
	public void deleteEvent(Event event) {
		eventRepository.delete(event);
	}
	
	public Event findEventById(Long id) {
	    Optional<Event> optionalEvent = eventRepository.findById(id);
	    if(optionalEvent.isPresent()) {
	        return optionalEvent.get();
	    } else {
	        return null;
	    }
	}
	
	public void addUser(User u, Event eve) {
        eve.setUser(u);
        eventRepository.save(eve);
    }
	

	public Event updateEvent(Long event_id, House house, String name, String details, String location, Date date, Date time){
		Optional<Event> optionalEvent = eventRepository.findById(event_id);
	    if(optionalEvent.isPresent()) {
	        Event event = optionalEvent.get();
	        event.setName(name);
	        event.setDate(date);
	        event.setLocation(location);
	        event.setDetails(details);
	        event.setTime(time);
	        event.setHouse(house);
	   	 	eventRepository.save(event);
	   	 	return event;
	    } else {
	        return null;	
	    }
	}
	
	public List<Event> getAllEvents(){
		return eventRepository.findAll();
	}
	
	
}
