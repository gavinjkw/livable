package com.gavinwilson.livable.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.gavinwilson.livable.models.EventMessage;
import com.gavinwilson.livable.models.User;
import com.gavinwilson.livable.services.EventMessageService;
import com.gavinwilson.livable.services.HouseService;
import com.gavinwilson.livable.services.UserService;

@Controller
public class EventMessageController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private HouseService houseService;
	
	@Autowired
	EventMessageService messageService;
	
	@RequestMapping("/add_event_message")
    public String home(HttpSession session, Model model, @ModelAttribute("messageObj") EventMessage message, BindingResult result, @RequestParam("eventId") String eventId) {
		long userId = (long) session.getAttribute("userId");	
    	User user = userService.findUserById(userId);
		messageService.createMessage(message);
		model.addAttribute(user);
    	String url = "redirect:/events/" + eventId;
        return url;
    }
}

