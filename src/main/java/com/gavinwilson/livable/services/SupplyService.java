package com.gavinwilson.livable.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gavinwilson.livable.models.Supply;
import com.gavinwilson.livable.repositories.SupplyRepository;

@Service
public class SupplyService {
	
	@Autowired
	private SupplyRepository supplyRepository;
	
	public Supply createSupply(Supply supply) {
		return supplyRepository.save(supply);	}
	
	
	public void deleteEvent(Supply supply) {
		supplyRepository.delete(supply);
	}
}
