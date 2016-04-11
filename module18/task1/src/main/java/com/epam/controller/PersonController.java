package com.epam.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.model.Account;
import com.epam.model.Person;
import com.epam.service.AccountService;
import com.epam.service.PersonService;


@Controller
public class PersonController {

	@Autowired
	private PersonService service;
	
	@Autowired
	private AccountService accountService;
	
	private static final Logger log = Logger.getLogger(PersonController.class);
	
	
	@RequestMapping(value = "/newPerson" , method = RequestMethod.GET)
	public String goToAddClientPage(Model model){
		return "newPerson";
	}
	
	@RequestMapping(value = "/addClient" , method = RequestMethod.POST)
	public String addClient(@ModelAttribute("client") Person client, Model model){
		log.debug("Add new client with passport number:" + client.getPassportNumber());
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
	
	@RequestMapping(value = "/showClients" , method = RequestMethod.GET)
	public String getClients(Model model){
		String result = "clients";
		try {
			List<Person> clients = service.getPersons();
			if (clients.isEmpty()) {
				model.addAttribute("error", "No clients");
				return "error";
			}
			model.addAttribute("clients", clients);
		} catch (Exception e) {
			model.addAttribute("error",  "Error occured. Please see log");
			result = "error";
			log.error(PersonController.class, e);
			
		}
		return result;
	}
	@RequestMapping(value = "/getClient" , method = RequestMethod.GET)
	public String getClient(Model model){
		return "searchPerson";
	}
	
	@RequestMapping(value = "/getPerson" , method = RequestMethod.POST)
	public String getClient(@ModelAttribute("passport") String passNumber, Model model){
		log.debug("Search person with passport number:" +passNumber);
		String result = "searchPerson";
		try {
			Person person = service.searchPerson(passNumber);
			if(person == null){
				log.debug("Person with passport number:" +passNumber +" was not found");
				model.addAttribute("error", "Person was not found");
				return "error";
			}
			log.debug("Person was found");
			model.addAttribute("passport", person.getPassportNumber());
			model.addAttribute("first_name", person.getName());
			model.addAttribute("last_name", person.getSurname());
			model.addAttribute("person", person);
		} catch (Exception e) {
			model.addAttribute("error",  "Error occured. Please see log");
			result = "error";
			log.error(PersonController.class, e);
		}
		return result;
	}
	
	@RequestMapping(value = "/assign" , method = RequestMethod.GET)
	public String assign(Model model){
		String result = "assignPerson";
		try {
			List<Person> persons = service.getPersons();
			if(persons.isEmpty()){
				model.addAttribute("error", "No persons for assign");
				return "error";
			}
			List<Account> accounts = accountService.getAccounts();
			if(accounts.isEmpty()){
				model.addAttribute("error", "No persons for assign");
				return "error";
			}
			model.addAttribute("persons", persons);
			model.addAttribute("accounts", accounts);
		} catch (Exception e) {
			log.error(PersonController.class, e);
			model.addAttribute("error", e.getMessage());
			result = "error";
		}
		return result;
	}
	
	@RequestMapping(value = "/assignPerson" , method = RequestMethod.POST)
	public String assignPerson(@ModelAttribute("person") String passNumber, @ModelAttribute("account") int accountID, Model model){
		log.debug("Assign person to account");
		String result = "index";
		try {
			Person person = service.searchPerson(passNumber);
			Account account = accountService.searchAccount(accountID);
			if (account == null || person == null) {
				model.addAttribute("error", "No data for assign");
				return "error";
			}
			accountService.assignPerson(person, account);
		} catch (Exception e) {
			log.error(PersonController.class, e);
			model.addAttribute("error", "Error occured. Please see log file");
			result = "error";
		}
		return result;
		
	}
	
}
