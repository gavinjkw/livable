package com.gavinwilson.livable.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gavinwilson.livable.models.Attend;
import com.gavinwilson.livable.models.Bill;
import com.gavinwilson.livable.models.Debt;
import com.gavinwilson.livable.models.Event;
import com.gavinwilson.livable.models.User;
import com.gavinwilson.livable.repositories.AttendRepository;
import com.gavinwilson.livable.repositories.BillRepository;
import com.gavinwilson.livable.repositories.DebtRepository;
import com.gavinwilson.livable.repositories.EventRepository;
import com.gavinwilson.livable.repositories.UserRepository;

@Service
public class DebtService {

	@Autowired
	private DebtRepository debtRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private UserService userService;
	
	public Debt createDebt(Debt debt ) {
		return debtRepository.save(debt);
	}
	
	public  List <Debt> createDebts(Bill bill, List<Long> userIds) {
		List<Debt> debtList = new ArrayList();
		
		for (Long item : userIds) {
		User user = userService.findUserById(item);
			
		Debt debt = new Debt();
		debt.setUser(user);
		debt.setBill(bill);
		debtRepository.save(debt);
		debtList.add(debt);
		}
		return debtList;
	}
	
	
	public List<Debt> getDebtByUserId(Long userId){
		return debtRepository.getDebtByUserId(userId);
	}
	
	public List<Debt> getDebtByBillId(Long billId){
		return debtRepository.getDebtByBillId(billId);
	}
	
	@Transactional
	public void deleteDebtsByBillId(Bill bill){
		debtRepository.deleteInBulkByBillId(bill);
	}
	
	public void deleteDebt(Long user_id, Long bill_id){

		  
		  Optional<User> optionalUser = userRepository.findById(user_id);
		  Optional<Bill> optionalBill = billRepository.findById(bill_id);

	      User newUser = optionalUser.get();

	      Bill newBill  = optionalBill.get();
	      
	      Debt debt = debtRepository.findByUserAndBill(newUser, newBill);

	      debtRepository.delete(debt);        	
	}
	
}
