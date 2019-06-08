package com.gavinwilson.livable.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.gavinwilson.livable.models.House;
import com.gavinwilson.livable.models.User;
import com.gavinwilson.livable.services.HouseService;
import com.gavinwilson.livable.services.UserService;
import com.gavinwilson.livable.validator.UserValidator;


@Controller
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private HouseService houseService;
	
	@Autowired 
	private UserValidator userValidator;
	
    @RequestMapping("/")
    public String index( HttpSession session) {
    	session.setAttribute("houseStatus", "0");
    	return "Index.jsp";
    }
    
    @RequestMapping("/account")
    public String accountNew(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	if (session.getAttribute("houseStatus") == null) {
			session.setAttribute("houseStatus", "0");
		}
    	return "loginPage.jsp";
    }
    
	@RequestMapping(value="/newHouse")
    public String register(HttpSession session,  Model model) {
		session.setAttribute("houseStatus", "2");
		return "redirect:/account";
        }
	
	@RequestMapping(value="/joinHouse")
    public String login(HttpSession session,  Model model) {
		session.setAttribute("houseStatus", "1");
		return "redirect:/account";
        }
	
	@RequestMapping(value="/resetHouse")
    public String reset(HttpSession session,  Model model) {
		session.setAttribute("houseStatus", "0");
		return "redirect:/account";
        }
	

    @PostMapping(value="/registration")
    public String registerUserNewHouse(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session,  Model model, @RequestParam("housePin") String housePin, @RequestParam(value="housePinConfirmation", required=false) String housePinConfirmation, @RequestParam("houseName") String houseName, @RequestParam("email") String email) {	
    	userValidator.validate(user, result);
    	User userOne  = userService.findByEmail(email);
    	if (houseName == "") {
			model.addAttribute("noHouseName", "Please provide a house name"); 
		}
		if (housePin == "") {
			model.addAttribute("noHousePin", "Please provide a house pin"); 
		}
		System.out.println(housePinConfirmation);
		if (housePinConfirmation != null) {
			System.out.println(housePin);
			System.out.println(housePinConfirmation);
			if (!housePin.equals(housePinConfirmation)) {
				model.addAttribute("pinMismatch", "The house pins do not match"); 
			if (houseService.findHouseByName(houseName) != null) {
				model.addAttribute("houseNameInUse", "That house name is in use"); 
			}
			}
		}else{
			
			House house = houseService.findHouseByName(houseName);
			System.out.println(house);
			if (house == null) {
				model.addAttribute("noHouse", "That house was not found"); 
			}
			else {
				String pinStr =	new Integer(house.getPin()).toString(); 
				System.out.println(pinStr + housePin);
				if(!housePin.equals(pinStr)) {
					model.addAttribute("wrongPin", "Incorrect pin"); 
				}
			}
		}
		
		if (email == "") {
			model.addAttribute("noEmail", "Please provide an email"); 
    	}
		if (userOne != null) {
			model.addAttribute("emailInUse", "Email address is in use"); 
    	}
		
		if (model.containsAttribute("noHouse") || model.containsAttribute("emailInUse") || model.containsAttribute("noEmail") || model.containsAttribute("wrongPin") || model.containsAttribute("noHousePin") || model.containsAttribute("noHouseName") || model.containsAttribute("pinMismatch") || result.hasErrors() || model.containsAttribute("noHouseName") || model.containsAttribute("houseNameInUse")) {
			return "loginPage.jsp";
		 
        } else {
        	Integer intPin = Integer.valueOf(housePin);	
	        User u = userService.registerUser(user);
	        House house = houseService.createHouse(houseName, intPin);
	        userService.addHouse(house, u);
	        session.setAttribute("userId", u.getId());
	        session.setAttribute("houseId", house.getId());
	        return "redirect:/home";
        }
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@ModelAttribute("user") User user, @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {

    	boolean isAuthenticated = userService.authenticateUser(email, password);
    	if (isAuthenticated){
    		 User userOne  = userService.findByEmail(email);
	    	 session.setAttribute("userId", userOne.getId());
	    	 House house = userOne.getHouse();
	    	 session.setAttribute("houseId", house.getId());
	         return "redirect:/home";
	     } else {
	    	 model.addAttribute("error", "Invalid credentials. Please try again.");
	    	 return "loginPage.jsp";
	     }
    }
    
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
        // redirect to login page
    	session.invalidate();
    	return "redirect:/";
    }
}