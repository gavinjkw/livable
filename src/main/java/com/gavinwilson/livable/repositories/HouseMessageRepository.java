package com.gavinwilson.livable.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.gavinwilson.livable.models.HouseMessage;



@Repository
public interface HouseMessageRepository extends CrudRepository<HouseMessage, Long>{
	
	
}
