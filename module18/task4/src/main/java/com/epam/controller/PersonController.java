package com.epam.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.model.Person;
import com.epam.service.PersonService;

@Controller
public class PersonController {
	
	private static final Logger log = Logger.getLogger(PersonController.class);
	
	@Autowired
	PersonService service;
	
	@RequestMapping(value = "/newPerson" , method = RequestMethod.GET)
	public String goToAddClientPage(Model model){
		return "newPerson";
	}
	
	@RequestMapping(value = "/addClient" , method = RequestMethod.POST)
	public String addClient(@ModelAttribute("client") Person client, Model model){
		log.debug("Add new client with name:" + client.getName()+" "+client.getSurname());
		String result = "index";
		try {
			service.addPerson(client);
		} catch (Exception e) {
			log.error(PersonController.class, e);
			model.addAttribute("error", e.getMessage());
			result = "error";
		}
		return result;
	}
}
