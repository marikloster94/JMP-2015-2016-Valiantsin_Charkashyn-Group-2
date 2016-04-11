package com.epam.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.dao.RateDAO;
import com.epam.exception.ExchangeException;
import com.epam.exception.HsqlDBException;
import com.epam.model.Account;
import com.epam.model.ExchangeRate;
import com.epam.model.ExchangeTicket;

@Repository
public class ExchangeService {

	@Autowired
	private RateDAO dao ;	
	
	@Transactional
	public ExchangeRate create(ExchangeRate rate) throws HsqlDBException{
		if(rate != null){
			rate = dao.create(rate);
		}
		return rate;
	}
	
	public synchronized List<ExchangeRate> getExchangeRates() throws Exception {
			return dao.getAll();
	}

	public ExchangeRate searchExchange(Date exchangeDate, String currencyFrom, String currencyTo) throws Exception {
		List<ExchangeRate> rates = getExchangeRates();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String date = format.format(exchangeDate);
		for (ExchangeRate rate : rates) {
			if(rate.getExchangeDay().equals(date) && rate.getFrom().equalsIgnoreCase(currencyFrom) &&
					rate.getTo().equalsIgnoreCase(currencyTo)){
				return rate;
			}
		}
		return null;
	}
	
	public double convert(ExchangeRate rate, Account accountFrom, List<Account> userAccounts, ExchangeTicket order) throws ExchangeException{
		double availableValue = accountFrom.getValue();
		double expectedValue = order.getToCurrAmount();
		if(availableValue >= expectedValue ){
			accountFrom.setValue(new BigDecimal(availableValue - expectedValue).doubleValue());
		}else{
			throw new ExchangeException("Cann't exchange value.");
		}
		double neededValue = calculate(rate, expectedValue);
		for(Account acc: userAccounts){
			if(acc.getCurr().equals(order.getFromCurr())){
				double userAvailValue = acc.getValue();
				if(userAvailValue >= neededValue ){
					BigDecimal bd = new BigDecimal(userAvailValue - neededValue);
					acc.setValue(bd.setScale(2, RoundingMode.HALF_UP).doubleValue());
				}else{
					throw new ExchangeException("Cann't exchange value.");
				}
			}
			if(acc.getCurr().equals(order.getToCurr())){
				BigDecimal bd = new BigDecimal(acc.getValue() + expectedValue);
				bd = bd.setScale(2, RoundingMode.HALF_UP);
				acc.setValue(bd.doubleValue());
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
