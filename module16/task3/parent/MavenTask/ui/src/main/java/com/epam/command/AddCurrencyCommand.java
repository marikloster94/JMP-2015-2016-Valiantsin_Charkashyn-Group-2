package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.epam.model.Currency;
import com.epam.service.CurrencyService;

public class AddCurrencyCommand implements Command {

	private static final Logger log = Logger.getLogger(AddCurrencyCommand.class);
	
	
	private CurrencyService currService;
	
	public AddCurrencyCommand(WebApplicationContext context) {
		currService = context.getBean(CurrencyService.class);
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Add new currency");
		String result = "/main.jsp";
		String shortName = request.getParameter("shortName");
		Currency curr = new Currency();
		curr.setShortName(shortName);
		try {
			currService.addCurrency(curr);
		} catch (Exception e) {
			log.error(AddCurrencyCommand.class, e);
			request.setAttribute("error", e.getMessage());
			result = "/error.jsp";
		}
		return result;
	}

}
