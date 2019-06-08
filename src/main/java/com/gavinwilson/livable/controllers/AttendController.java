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
import com.gavinwilson.livable.services.AttendService;


@Controller
public class AttendController {
	
	@Autowired
	private AttendService attendService;
	
	@PostMapping(value="/joinEvent")
	public String create(@Valid @ModelAttribute("middleTableObj") Attend attend, BindingResult result) {
        attendService.createAttend(attend);

        return "redirect:/events";
	}
	
    @DeleteMapping(value="/leaveEvent/{user_id}/{event_id}")
    	 public String delete(@Valid @ModelAttribute("attend") Attend cS, BindingResult result, @PathVariable("event_id") Long event_id, @PathVariable("user_id") Long user_id) {
    	         attendService.deleteAttend(user_id, event_id);
    	         return "redirect:/events";
    	 }
	}
