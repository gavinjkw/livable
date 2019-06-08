package com.gavinwilson.livable.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gavinwilson.livable.models.Event;
import com.gavinwilson.livable.models.EventMessage;



@Repository
public interface EventMessageRepository extends CrudRepository<EventMessage, Long>{
	
	
}
