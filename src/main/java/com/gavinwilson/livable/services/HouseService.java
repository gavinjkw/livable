package com.gavinwilson.livable.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gavinwilson.livable.models.House;
import com.gavinwilson.livable.repositories.HouseRepository;

	@Service
	public class HouseService {

		@Autowired
		private HouseRepository houseRepository;
		
		public House createHouse(String houseName, Integer housePin) {
	        House house = houseRepository.findByNameAndPin(houseName, housePin);
	        if(house == null) {
	            House houseOne = new House();
	            houseOne.setName(houseName);
	            houseOne.setPin(housePin);
	            House newHouse = houseRepository.save(houseOne);
	            return newHouse;
	        } else {
	        	return house;  
	       }
	    }
		
		
		
		public House findHouseByName(String houseName) {
			House house = houseRepository.findByName(houseName);
	        if(house == null) {
	            return null;
	        } else {
	        	return house;  
	       }
		}
		
		public House findHouseById(Long houseId) {
			Optional<House> optionalHouse = houseRepository.findById(houseId);
			if(optionalHouse.isPresent()) {
	            return optionalHouse.get();
	        } else {
	            return null;
	        }
		}
		
	}