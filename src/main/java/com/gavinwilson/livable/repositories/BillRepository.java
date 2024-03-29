package com.gavinwilson.livable.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gavinwilson.livable.models.Bill;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long>{

}
