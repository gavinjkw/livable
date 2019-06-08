package com.gavinwilson.livable.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gavinwilson.livable.models.Attend;
import com.gavinwilson.livable.models.Bill;
import com.gavinwilson.livable.models.Event;
import com.gavinwilson.livable.models.House;
import com.gavinwilson.livable.models.User;
import com.gavinwilson.livable.services.AttendService;
import com.gavinwilson.livable.services.DebtService;
import com.gavinwilson.livable.services.EventMessageService;
import com.gavinwilson.livable.services.HouseService;
import com.gavinwilson.livable.services.UserService;

@Controller
public class BillsController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private BillService billService;
	
	@Autowired 
	private DebtService debtService;
	
	@Autowired 
	private EventMessageService messageService;
	
	@Autowired 
	private HouseService houseService;
	
	@RequestMapping("/bills")
    public String home(HttpSession session, Model model, @Valid @ModelAttribute("billObj") Bill bill, BindingResult result) {
    	long userId = (long) session.getAttribute("userId");	
    	User user = userService.findUserById(userId);
    	model.addAttribute(user);

        long houseId = (long) session.getAttribute("houseId");
    	House house = houseService.findHouseById(houseId);
    	model.addAttribute("house", house);
        return "Bills.jsp";
    }
	
	@PostMapping("/bills")
    public String addEvent(HttpSession session, Model model, @Valid @ModelAttribute("billObj") Bill bill, BindingResult result, @RequestParam("debts") List<Long> debts, @RequestParam(value="amountDueIndiv", required=false) String amountDue) {
		long userId = (long) session.getAttribute("userId");	
    	User user = userService.findUserById(userId);
    	model.addAttribute(user);
    	long houseId = (long) session.getAttribute("houseId");
    	House house = houseService.findHouseById(houseId);
    	model.addAttribute("house", house);
    	System.out.println(debts);
    	Integer count = 0;
    	for(Long item : debts) {
    		count++;
    		};

	
		if (result.hasErrors()) {
	        return "Bills.jsp";
		
	     } else {
	         Bill b = billService.createBill(bill);
	         Long uId = (Long) session.getAttribute("userId");
	         User u = userService.findUserById(uId);
	         billService.addUser(u, b);
	         
	         if (amountDue != "") {
	        	 Double amountOwed = (bill.getTotal() / ((count) + 1));
	        	 billService.addIndivDue(amountOwed, b);
	        	 debtService.createDebts(bill, debts);
	         }
	         else {
	        	 Double amountOwed = Double.valueOf(amountDue);
	        	 billService.addIndivDue(amountOwed, b);
	        	 debtService.createDebts(bill, debts);
	         }
	        
	         return "redirect:/bills";
	     }
	 }
	
	@DeleteMapping("/delete_bill/{bill_id}")
	public String deleteBill (@PathVariable("bill_id") Long bill_id) {
		Bill bill = billService.findBillById(bill_id);
		debtService.deleteDebtsByBillId(bill);
		billService.deleteBill(bill);
		return "redirect:/bills";
	}
}
