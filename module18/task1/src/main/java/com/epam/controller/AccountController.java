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
import com.epam.model.Currency;
import com.epam.model.Person;
import com.epam.service.AccountService;
import com.epam.service.CurrencyService;
import com.epam.service.PersonService;

@Controller
public class AccountController {
	
	@Autowired
	private PersonService service;
	
	@Autowired
	private CurrencyService currService;
	
	@Autowired
	private AccountService accService;
	
	private static final Logger log = Logger.getLogger(AccountController.class);
	

	@RequestMapping(value = "/openAccount" , method = RequestMethod.GET)
	public String openAccount(Model model){
		String result = "addAccount";
		try {
			List<Currency> currencies = currService.getCurrencies();
			List<Person> people = service.getPersons();
			if(currencies.isEmpty() || people.isEmpty()){
				model.addAttribute("error", "No data for open account");
				return "error";
			}
			model.addAttribute("persons", people);
			model.addAttribute("currencies", currencies);
		} catch (Exception e) {
			model.addAttribute("error", "No data for open account");
			log.error(AccountController.class, e);
			result = "error";
		}
		return result;
	}
	
	@RequestMapping(value = "/addAccount" , method = RequestMethod.POST)
	public String addAccount(@ModelAttribute("account") Account acc, @ModelAttribute("person") String passNumber, @ModelAttribute("currency") String currency, Model model){
		log.debug("Add new account with data:"+acc.getDescription() + " "+acc.getValue());
		String result = "index";
		Person client = null;
		try {
			client = service.searchPerson(passNumber);
		} catch (Exception e) {
			log.error(AccountService.class, e);
		}
		if(client == null){
			model.addAttribute("error", "There is not client with passport number "+passNumber+". You should create new person");
			return "error";
		}
		acc.setMan(client);
		Currency curr = null;
		try {
			curr = currService.searchCurrency(currency);
		} catch (Exception e) {
			log.error(AccountService.class, e);
		}
		if (curr == null) {
			model.addAttribute("error", "Currency can not be null");
			return "error";
		}
		acc.setCurr(curr);
		try {
			accService.addAccount(acc);
		} catch (Exception e) {
			log.error(AccountService.class, e);
			model.addAttribute("error", e.getMessage());
			result = "error";
		}
		return result;
	}
	
	@RequestMapping(value = "/getAccounts" , method = RequestMethod.GET)
	public String getAccounts(Model model){
		String result = "accounts";
		try {
			List<Account> accounts = accService.getAccounts();
			if (accounts.isEmpty()) {
				model.addAttribute("error", "No Accounts");
				return "error";
			}
			model.addAttribute("accounts", accounts);
		} catch (Exception e) {
			model.addAttribute("error", "Error occured. Please see log");
			log.error(AccountController.class, e);
			result = "error";
		}
		return result;
	}
	
	@RequestMapping(value = "/getAccount" , method = RequestMethod.GET)
	public String getAccount(Model model){
		return "searchAccount";
	}
	
	@RequestMapping(value = "/getAccount" , method = RequestMethod.POST)
	public String getAccount(@ModelAttribute("id") int accId, Model model){
		log.debug("Search account");
		String result = "searchAccount";
		try {
			Account account = accService.searchAccount(accId);
			if (account == null) {
				log.debug("Account was not found");
				model.addAttribute("error", "Account was not found");
				return "error";
			}
			log.debug("Account was found");
			model.addAttribute("account", account);
			model.addAttribute("id", accId);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			log.error(AccountController.class, e);
			result = "error";
		}
		return result;
	}
}
