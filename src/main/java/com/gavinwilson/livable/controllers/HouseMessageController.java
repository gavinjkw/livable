package com.gavinwilson.livable.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gavinwilson.livable.models.House;
import com.gavinwilson.livable.models.HouseMessage;
import com.gavinwilson.livable.models.User;
import com.gavinwilson.livable.services.HouseMessageService;
import com.gavinwilson.livable.services.HouseService;
import com.gavinwilson.livable.services.UserService;

@Controller
public class HouseMessageController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private HouseService houseService;
	
	@Autowired
	HouseMessageService messageService;
	
	
	@RequestMapping("/house_messages")
    public String messages(HttpSession session, Model model, @ModelAttribute("messageObj") HouseMessage message, BindingResult result) {
		long userId = (long) session.getAttribute("userId");	
    	User user = userService.findUserById(userId);
    	model.addAttribute(user);
    	long houseId = (long) session.getAttribute("houseId");
    	House house = houseService.findHouseById(houseId);
    	model.addAttribute(house);
    	return "Messages.jsp";
	}
    	
	@RequestMapping("/add_house_message")
    public String addMessage(HttpSession session, Model model, @ModelAttribute("messageObj") HouseMessage message, BindingResult result) {
		long userId = (long) session.getAttribute("userId");	
    	User user = userService.findUserById(userId);
		messageService.createMessage(message);

		return "redirect:/house_messages";
    }
}

