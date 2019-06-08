package com.gavinwilson.livable.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.gavinwilson.livable.models.House;

@Repository
public interface HouseRepository extends CrudRepository<House, Long> {

	House findByNameAndPin(String name, Integer pin);
	
	House findByName(String name);
	
	Optional<House> findById(Long id);
}
