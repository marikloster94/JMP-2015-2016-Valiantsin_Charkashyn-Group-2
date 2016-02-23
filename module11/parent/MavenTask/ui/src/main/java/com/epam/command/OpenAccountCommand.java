package com.epam.command;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.model.Account;
import com.epam.model.Currency;
import com.epam.model.Person;
import com.epam.service.AccountService;
import com.epam.service.CurrencyService;
import com.epam.service.PersonService;

public class OpenAccountCommand implements Command {
	
	private static final Logger log = Logger.getLogger(OpenAccountCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Add new account");
		String result = "/main.jsp";
		String endDate = (String) request.getParameter("endDate");
		String startDate = (String) request.getParameter("startDate");
		String description = (String) request.getParameter("description");
		String currency = request.getParameter("currency");
		double amount = Double.parseDouble(request.getParameter("amount"));
		String passport = (String) request.getParameter("person");
		AccountService accountServ = new AccountService();
		Account acc = new Account();
		PersonService service = new PersonService();
		
		Person person = null;
		try {
			person = service.searchPerson(passport);
		} catch (SQLException e) {
			log.error(AddPersonCommand.class, e);
		}
		if(person == null){
			request.setAttribute("error", "There is not client with passport number "+passport+". You should create new person");
			return "/error";
		}
		acc.setPerson(person);
		CurrencyService currService = new CurrencyService();
		Currency curr = null;
		try {
			curr = currService.searchCurrency(currency);
		} catch (SQLException e) {
			log.error(AddPersonCommand.class, e);
		}
		if (curr == null) {
			request.setAttribute("error", "Currency can not be null");
			return "/error";
		}
		acc.setCurr(curr);
		acc.setDescription(description);
		acc.setStartDate(startDate);
		acc.setEndDate(endDate);
		acc.setValue(new BigDecimal(amount));
		try {
			accountServ.addAccount(acc);
		} catch (Exception e) {
			log.error(AddPersonCommand.class, e);
			request.setAttribute("error", e.getMessage());
			result = "/error.jsp";
		}
		return result;
	}

}
