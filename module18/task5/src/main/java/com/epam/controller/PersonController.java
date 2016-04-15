package com.epam.controller;

import java.util.List;

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
	
	@RequestMapping(value = "/updatePerson", method = RequestMethod.GET)
	public  String prepareForUpdateClient(Model model){
		String result = "updatePerson";
		try {
			List<Person> people = service.getAll();
			if(people.isEmpty()){
				model.addAttribute("error", "No data for add booking");
				return "error";
			}
			model.addAttribute("persons", people);
		} catch (Exception e) {
			log.error(PersonController.class, e);
			model.addAttribute("error", e.getMessage());
			result = "error";
		}
		return result;
	}
	
	@RequestMapping(value = "/saveChanges", method = RequestMethod.POST)
	public  String saveChanges(@ModelAttribute("client") Person client, @ModelAttribute("person") int idPerson, Model model){
		client.setIdPerson(idPerson);
		String result = "index";
		try {
			service.updatePerson(client);
		} catch (Exception e) {
			log.error(PersonController.class, e);
			model.addAttribute("error", e.getMessage());
			result = "error";
		}
		return result;
	}
}
