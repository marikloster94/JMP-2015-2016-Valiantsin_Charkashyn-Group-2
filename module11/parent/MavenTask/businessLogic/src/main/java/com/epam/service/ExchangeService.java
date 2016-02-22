package com.epam.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.epam.dao.RateDAO;
import com.epam.exception.ExchangeException;
import com.epam.model.Account;
import com.epam.model.ExchangeRate;
import com.epam.model.ExchangeTicket;

public class ExchangeService {

	private static final RateDAO dao = new RateDAO();	
	
	public synchronized List<ExchangeRate> getExchangeRates() throws SQLException {
			return dao.getAll();
	}

	public ExchangeRate searchExchange(Date exchangeDate, String currencyFrom, String currencyTo) throws SQLException {
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
		double availableValue = accountFrom.getValue().setScale(2, RoundingMode.HALF_UP).doubleValue();
		double expectedValue = order.getToCurrAmount().setScale(2, RoundingMode.HALF_UP).doubleValue();
		if(availableValue >= expectedValue ){
			accountFrom.setValue(new BigDecimal(availableValue - expectedValue));
		}else{
			throw new ExchangeException("Cann't exchange value.");
		}
		double neededValue = calculate(rate, expectedValue);
		for(Account acc: userAccounts){
			if(acc.getCurr().equals(order.getFromCurr())){
				double userAvailValue = acc.getValue().doubleValue();
				if(userAvailValue >= neededValue ){
					BigDecimal bd = new BigDecimal(userAvailValue - neededValue);
					acc.setValue(bd.setScale(2, RoundingMode.HALF_UP));
				}else{
					throw new ExchangeException("Cann't exchange value.");
				}
			}
			if(acc.getCurr().equals(order.getToCurr())){
				BigDecimal bd = new BigDecimal(acc.getValue().doubleValue() + expectedValue);
				bd = bd.setScale(2, RoundingMode.HALF_UP);
				acc.setValue(bd);
			}
		}
		order.setStatus("done");
		return neededValue;
		
	}
	
	public double calculate(ExchangeRate rate, double expectedValue ){
		double neededValue = expectedValue * rate.getRate().doubleValue();
		BigDecimal bd = new BigDecimal(neededValue);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
