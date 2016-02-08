package com.epam.mentoring.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.epam.mentoring.dao.IDAO;
import com.epam.mentoring.dao.RateDAO;
import com.epam.mentoring.exception.ExchangeException;
import com.epam.mentoring.model.Account;
import com.epam.mentoring.model.ExchangeRate;
import com.epam.mentoring.model.ExchangeTicket;

public class ExchangeService {

	private static final IDAO dao = new RateDAO();	
	
	public List<ExchangeRate> getExchangeRates() {
		return null;
//		return ((ExchangeRates) FileUtil.loadFromFile(client)).getRates();
	}

	public ExchangeRate searchExchange(Date exchangeDate, String currencyFrom, String currencyTo) {
		List<ExchangeRate> rates = getExchangeRates();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String date = format.format(exchangeDate);
		for (ExchangeRate rate : rates) {
			if(rate.getExchangeDay().equals(date) && rate.getFrom().getShortName().equalsIgnoreCase(currencyFrom) &&
					rate.getTo().getShortName().equalsIgnoreCase(currencyTo)){
				return rate;
			}
		}
		return null;
	}
	
	public double convert(ExchangeRate rate, Account accountFrom, List<Account> userAccounts, ExchangeTicket order) throws ExchangeException{
		double availableValue = accountFrom.getValue();
		double expectedValue = order.getToCurrAmount();
		if(availableValue >= expectedValue ){
			accountFrom.setValue(availableValue - expectedValue);
		}else{
			throw new ExchangeException("Cann't exchange value.");
		}
		double neededValue = calculate(rate, expectedValue);
		for(Account acc: userAccounts){
			if(acc.getCurr().equals(order.getFromCurr())){
				double userAvailValue = acc.getValue();
				if(userAvailValue >= neededValue ){
					acc.setValue(userAvailValue - neededValue);
				}else{
					throw new ExchangeException("Cann't exchange value.");
				}
			}
			if(acc.getCurr().equals(order.getToCurr())){
				acc.setValue(acc.getValue() + expectedValue);
			}
		}
		order.setStatus("done");
		return neededValue;
		
	}
	
	public double calculate(ExchangeRate rate, double expectedValue ){
		double neededValue = expectedValue * rate.getRate();
		BigDecimal bd = new BigDecimal(neededValue);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
