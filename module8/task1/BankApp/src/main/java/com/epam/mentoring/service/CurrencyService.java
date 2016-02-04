package com.epam.mentoring.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.epam.mentoring.exception.AddNewElementException;
import com.epam.mentoring.file.ClientThread;
import com.epam.mentoring.file.FileGenerator;
import com.epam.mentoring.jaxb.model.Currencies;
import com.epam.mentoring.model.Currency;
import com.epam.mentoring.util.FileUtil;

public class CurrencyService {
	private final ClientThread client = new ClientThread("currency.xml");

	public CurrencyService() {
		client.setClassName(Currencies.class);
	}

	public List<Currency> getCurrencies() {
		return ((Currencies) FileUtil.loadFromFile(client)).getCurrencies();
	}

	public Currency searchCurrency(String currency) {
		List<Currency> currencies = getCurrencies();
		for (Currency curr : currencies) {
			if (curr.getShortName().equals(currency)) {
				return curr;
			}
		}
		return null;
	}

	public void addCurrency(Currency currency) throws Exception {
		Currency foundCurrency = searchCurrency(currency.getShortName());
		if (foundCurrency == null) {
			List<Currency> currencies = getCurrencies();
			currencies.add(currency);
			Currencies curr = new Currencies();
			curr.setCurrencies(currencies);
			FileGenerator generator = new FileGenerator("currency.xml", curr);
			generator.setClassName(Currencies.class);
			FileUtil.writeToFile(generator);
		} else {
			 throw new AddNewElementException("You can't add this currency cause it exist");
		}
	}
	
	public int gelLastId(){
		List<Currency> currencies = getCurrencies();
		if(currencies == null){
			return 1;
		}
		Collections.sort(currencies, new Comparator<Currency>() {
			@Override
			public int compare(Currency o1, Currency o2) {
				return o2.getIdCurrency() - o1.getIdCurrency();
			}
		});
		return currencies.get(0).getIdCurrency()+1;
	}
}
