package com.gavinwilson.livable.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gavinwilson.livable.models.Attend;
import com.gavinwilson.livable.models.Event;
import com.gavinwilson.livable.models.User;

@Repository
public interface AttendRepository extends CrudRepository<Attend, Long>{

	List<Attend> getAttendByUserId(Long userId);
	
	Attend findByUserAndEvent(User user, Event event);
}
