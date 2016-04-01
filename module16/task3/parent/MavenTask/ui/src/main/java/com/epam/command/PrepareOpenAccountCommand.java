package com.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.epam.model.Currency;
import com.epam.model.Person;
import com.epam.service.CurrencyService;
import com.epam.service.PersonService;

public class PrepareOpenAccountCommand implements Command {
	
	private static final Logger log = Logger.getLogger(PrepareOpenAccountCommand.class);

	private PersonService service;
	private CurrencyService currService;
	
	public PrepareOpenAccountCommand(WebApplicationContext context) {
		currService =  context.getBean(CurrencyService.class);
		service =  context.getBean(PersonService.class);
	}
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String result = "/addAccount.jsp";
		try {
			List<Currency> currencies = currService.getCurrencies();
			List<Person> people = service.getPersons();
			if(currencies.isEmpty() || people.isEmpty()){
				request.setAttribute("error", "No data for open account");
				return "/error.jsp";
			}
			request.setAttribute("persons", people);
			request.setAttribute("currencies", currencies);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			log.error(SearchAccountCommand.class, e);
			result = "/error.jsp";
		}
		
		return result;
	}

}
