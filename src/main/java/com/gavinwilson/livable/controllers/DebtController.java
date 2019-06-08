package com.gavinwilson.livable.controllers;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gavinwilson.livable.models.Attend;
import com.gavinwilson.livable.models.Debt;
import com.gavinwilson.livable.services.AttendService;
import com.gavinwilson.livable.services.DebtService;


@Controller
public class DebtController {
	
	@Autowired
	private DebtService debtService;
	
	@PostMapping(value="/add_debt")
	public String create(@Valid @ModelAttribute("middleTableObj") Debt debt, BindingResult result) {
        debtService.createDebt(debt);

        return "redirect:/bills";
	}
	
    @DeleteMapping(value="/paid/{user_id}/{bill_id}")
    	 public String delete(@Valid @ModelAttribute("attend") Attend cS, BindingResult result, @PathVariable("bill_id") Long bill_id, @PathVariable("user_id") Long user_id) {
    	         debtService.deleteDebt(user_id, bill_id);
    	         return "redirect:/bills";
    	 }
	}
