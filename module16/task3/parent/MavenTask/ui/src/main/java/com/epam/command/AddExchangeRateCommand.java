package com.epam.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.epam.exception.HsqlDBException;
import com.epam.model.ExchangeRate;
import com.epam.service.ExchangeService;

public class AddExchangeRateCommand implements Command {

	private ExchangeService service;
	private static final Logger log = Logger.getLogger(AddExchangeRateCommand.class);
	
	
	
	public AddExchangeRateCommand(WebApplicationContext context){
		service = context.getBean(ExchangeService.class);
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String result = "/main.jsp";
		ExchangeRate rate = new ExchangeRate();
		rate.setExchangeDay("03/04/2016");
		rate.setTo("RUB");
		rate.setFrom("USD");
		rate.setRate(68.45);
		try {
			rate = service.create(rate);
			List<ExchangeRate> rates = service.getExchangeRates();
			if( rates.size() == 0 ){
				request.setAttribute("error", "no rates after adding");
				result = "/error.jsp";
			}
		} catch (Exception e) {
			log.error(AddCurrencyCommand.class, e);
			request.setAttribute("error", e.getMessage());
			result = "/error.jsp";
		}
		
		return result;
	}

}
