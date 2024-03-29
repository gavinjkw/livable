package com.gavinwilson.livable.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gavinwilson.livable.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long>{

	List<Event> findAll();
	

}
