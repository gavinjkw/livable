package com.gavinwilson.livable.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.gavinwilson.livable.models.Bill;
import com.gavinwilson.livable.models.Debt;
import com.gavinwilson.livable.models.User;

@Repository
public interface DebtRepository extends CrudRepository<Debt, Long>{

	List<Debt> getDebtByUserId(Long userId);
	
	List<Debt> getDebtByBillId(Long billId);
	
	@Modifying
	  @Query("delete from Debt d where d.bill = ?1")
	  void deleteInBulkByBillId(Bill bill);
	
	Debt findByUserAndBill(User user, Bill bill);
}
