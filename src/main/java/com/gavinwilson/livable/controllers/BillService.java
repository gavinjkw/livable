package com.gavinwilson.livable.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gavinwilson.livable.models.Bill;
import com.gavinwilson.livable.models.Event;
import com.gavinwilson.livable.models.User;
import com.gavinwilson.livable.repositories.BillRepository;


@Service 
public class BillService {
	
	@Autowired
	private BillRepository billRepository;
	
	public Bill createBill(Bill b) {
		return billRepository.save(b);	}
	
	public void addUser(User u, Bill b) {
        b.setUser(u);
        billRepository.save(b);
    }

	public void addIndivDue(Double amountOwed, Bill b) {
		b.setIndivDue(amountOwed);
        billRepository.save(b);
		
	}
	
	public void deleteBill(Bill bill) {
		billRepository.delete(bill);
	}

	public Bill findBillById(Long bill_id) {
		 Optional<Bill> optionalBill = billRepository.findById(bill_id);
		    if(optionalBill.isPresent()) {
		        return optionalBill.get();
		    } else {
		        return null;
		    }
		}
	
	
}
