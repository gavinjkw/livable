package com.gavinwilson.livable.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gavinwilson.livable.models.House;
import com.gavinwilson.livable.models.User;
import com.gavinwilson.livable.services.HouseService;
import com.gavinwilson.livable.services.UserService;

@Controller
public class HomeController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private HouseService houseService;
	
	@RequestMapping("/home")
    public String accountNew(Model model, HttpSession session) {
		long userId = (long) session.getAttribute("userId");	
    	User user = userService.findUserById(userId);
    	model.addAttribute(user);
    	return "HomePage.jsp";
    }
	
	@RequestMapping("/myhouse")
    public String myHouse(Model model, HttpSession session) {
		long userId = (long) session.getAttribute("userId");	
    	User user = userService.findUserById(userId);
    	model.addAttribute(user);
    	long houseId = (long) session.getAttribute("houseId");
    	House house = houseService.findHouseById(houseId);
    	model.addAttribute(house);
    	return "MyHouse.jsp";
    }
}
