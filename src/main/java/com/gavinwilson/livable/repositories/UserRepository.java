package com.gavinwilson.livable.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gavinwilson.livable.models.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	
	User findByEmail(String email);
	
}
