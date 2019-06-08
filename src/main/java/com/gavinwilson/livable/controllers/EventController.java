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
import com.gavinwilson.livable.models.Event;
import com.gavinwilson.livable.models.EventMessage;
import com.gavinwilson.livable.models.House;
import com.gavinwilson.livable.models.User;
import com.gavinwilson.livable.services.AttendService;
import com.gavinwilson.livable.services.EventService;
import com.gavinwilson.livable.services.HouseService;
import com.gavinwilson.livable.services.EventMessageService;
import com.gavinwilson.livable.services.UserService;

@Controller
public class EventController {

	@Autowired 
	private UserService userService;
	
	@Autowired 
	private EventService eventService;
	
	@Autowired 
	private AttendService attendService;
	
	@Autowired 
	private EventMessageService messageService;
	
	@Autowired 
	private HouseService houseService;
	
	@RequestMapping("/events")
    public String home(HttpSession session, Model model, @Valid @ModelAttribute("eventObj") Event event, BindingResult result, @ModelAttribute("middleTableObj") Attend attend) {
    	long userId = (long) session.getAttribute("userId");	
    	User user = userService.findUserById(userId);
    	model.addAttribute(user);
        List<Attend> a = attendService.getAttendByUserId(user.getId());
        model.addAttribute("attendingEvents", a);
        model.addAttribute("alreadyJoined", "not joined");
        long houseId = (long) session.getAttribute("houseId");
    	House house = houseService.findHouseById(houseId);
    	model.addAttribute("house", house);
        return "Events.jsp";
    }
	
	@PostMapping("/events")
    public String addEvent(HttpSession session, Model model, @Valid @ModelAttribute("eventObj") Event event, BindingResult result, @ModelAttribute("middleTableObj") Attend attend) {
		long userId = (long) session.getAttribute("userId");	
    	User user = userService.findUserById(userId);
    	model.addAttribute(user);

		if (event.getDate() == null) {
			model.addAttribute("noDate", "Please provide a date");
		}
	
		if (model.containsAttribute("noDate")|| result.hasErrors()) {
	        return "Events.jsp";
		
	     } else {
	         Event e = eventService.createEvent(event);
	         Long uId = (Long) session.getAttribute("userId");
	         User u = userService.findUserById(uId);
	         eventService.addUser(u, e);
	         attendService.createAttendManual(u, e);
	         return "redirect:/events";
	     }
	 }
	
	@DeleteMapping("/deleteEvent/{eventId}")
	public String deleteEvent (@PathVariable("eventId") Long eventId) {
		Event event = eventService.findEventById(eventId);
		eventService.deleteEvent(event);
		return "redirect:/events";
	}
	
	@RequestMapping("/events/{eventId}/edit")
    public String editEvent(HttpSession session, Model model, @PathVariable("eventId") Long eventId,  @Valid @ModelAttribute("eventObj") Event event) {
		long userId = (long) session.getAttribute("userId");	
    	User user = userService.findUserById(userId);
    	Event eventOne = eventService.findEventById(eventId);
		if(eventOne.getUser().equals(user)) {
	    	model.addAttribute(user);
	    	model.addAttribute(eventOne);
	    	return "EditEvent.jsp";
		}
		else {
			return "redirect:/events";
		}
    }
	
	@PostMapping("/edit_event/{eventId}")
    public String editEventProcess(HttpSession session, Model model, @Valid @ModelAttribute("eventObj") Event event, BindingResult result, @PathVariable("eventId") Long eventId, @RequestParam("name") String name, @RequestParam("date") String date) {	
		long userId = (long) session.getAttribute("userId");	
    	User user = userService.findUserById(userId);
    	Event eventOne = eventService.findEventById(eventId);
    	model.addAttribute(user);
    	model.addAttribute(eventOne);
    	long houseId = (long) session.getAttribute("houseId");
		String numberAsString = Long.toString(eventId);
		House house = houseService.findHouseById(houseId);
		System.out.println("This is step one");
		if (event.getDate() == null) {
			model.addAttribute("noDate", "Please provide a date");
		}
		if (model.containsAttribute("noDate")  ) {
			String url = "redirect:/events/" + numberAsString + "/edit";
			return url;
		} 
		if (result.hasErrors()) {
			String url = "redirect:/events/" + numberAsString + "/edit";
			return url;
		} else {
			eventService.updateEvent(eventId, house, event.getName(), event.getDetails(), event.getLocation(), event.getDate(), event.getTime());   
			return "redirect:/events";
		}  
		
    }
	
	@RequestMapping("/events/{eventId}")
	public String showIndivEvent(HttpSession session, Model model, @PathVariable("eventId") Long eventId, @ModelAttribute("messageObj") EventMessage message) {
		Event eventOne = eventService.findEventById(eventId);
		model.addAttribute(eventOne);
		
		long userId = (long) session.getAttribute("userId");	
    	User user = userService.findUserById(userId);
    	model.addAttribute(user);
        long houseId = (long) session.getAttribute("houseId");
    	House house = houseService.findHouseById(houseId);
    	model.addAttribute("house", house);
		return "ShowEvent.jsp";
    
}
}	

