package com.epam.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.model.Currency;
import com.epam.model.Person;
import com.epam.service.CurrencyService;
import com.epam.service.PersonService;

public class PrepareOpenAccountCommand implements Command {
	
	private static final Logger log = Logger.getLogger(PrepareOpenAccountCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		CurrencyService currService  = new CurrencyService();
		PersonService service = new PersonService();
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
