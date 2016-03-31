package com.epam.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.model.Currency;
import com.epam.service.CurrencyService;

public class AddCurrencyCommand implements Command {

	private static final Logger log = Logger.getLogger(AddCurrencyCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Add new currency");
		String result = "/main.jsp";
		String shortName = request.getParameter("shortName");
		CurrencyService service = new CurrencyService();
		Currency curr = new Currency();
		curr.setShortName(shortName);
		try {
			service.addCurrency(curr);
		} catch (Exception e) {
			log.error(AddCurrencyCommand.class, e);
			request.setAttribute("error", e.getMessage());
			result = "/error.jsp";
		}
		return result;
	}

}
