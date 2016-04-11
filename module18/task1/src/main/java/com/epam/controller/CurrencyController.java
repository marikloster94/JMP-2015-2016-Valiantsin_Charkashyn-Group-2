package com.epam.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.model.Currency;
import com.epam.service.CurrencyService;

@Controller
public class CurrencyController {
	
	@Autowired
	private CurrencyService currService;
	
	private static final Logger log = Logger.getLogger(CurrencyController.class);
	
	
	@RequestMapping(value = "/newCurrency" , method = RequestMethod.GET)
	public String goToAddCurrencyPage(Model model){
		return "newCurrency";
	}
	
	@RequestMapping(value = "/addCurrency" , method = RequestMethod.POST)
	public String addCurrency(@ModelAttribute("shortName") String shortName, Model model){
		log.debug("Add new currency with short name:" + shortName);
		String result = "index";
		Currency curr = new Currency();
		curr.setShortName(shortName);
		try {
			currService.addCurrency(curr);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			result = "error";
		}
		return result;
	}

}
