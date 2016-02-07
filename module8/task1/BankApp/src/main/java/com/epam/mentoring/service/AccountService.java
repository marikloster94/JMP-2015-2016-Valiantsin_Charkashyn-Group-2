package com.epam.mentoring.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.epam.mentoring.exception.ElementNotFoundException;
import com.epam.mentoring.file.ClientThread;
import com.epam.mentoring.file.FileGenerator;
import com.epam.mentoring.jaxb.model.Accounts;
import com.epam.mentoring.jaxb.model.Persons;
import com.epam.mentoring.model.Account;
import com.epam.mentoring.model.Person;
import com.epam.mentoring.util.FileUtil;

public class AccountService {
	private final ClientThread client = new ClientThread("account.xml");
	private final Accounts accs = new Accounts();

	public AccountService() {
		client.setClassName(Accounts.class);
	}

	public List<Account> getAccounts() {
		return ((Accounts) FileUtil.loadFromFile(client)).getAccounts();
	}

	public int getLastId() {
		List<Account> accounts = getAccounts();
		if (accounts == null) {
			return 1;
		}
		Collections.sort(accounts, new Comparator<Account>() {
			@Override
			public int compare(Account o1, Account o2) {
				return o2.getId() - o1.getId();
			}
		});
		return accounts.get(0).getId() + 1;
	}

	public void addAccount(Account acc) {
		List<Account> accounts = getAccounts();
		accounts.add(acc);
		accs.setAccounts(accounts);
		FileGenerator generator = new FileGenerator("account.xml", accs);
		generator.setClassName(Accounts.class);
		FileUtil.writeToFile(generator);
	}

	public void assignPerson(Person person, Account account) throws Exception {
		if (account == null) {
			throw new ElementNotFoundException(
					"You can't assign person to nonexistent account");
		}
		if (person == null) {
			throw new ElementNotFoundException(
					"You can't assign nonexistent person to account");
		}
		List<Account> accounts = getAccounts();
		accounts.remove(account);
		account.setPerson(person);
		accounts.add(account);
		accs.setAccounts(accounts);
		FileGenerator generator = new FileGenerator("account.xml", accs);
		generator.setClassName(Accounts.class);
		FileUtil.writeToFile(generator);
	}

	public Account searchAccount(int id) {
		List<Account> accounts = getAccounts();
		for (Account acc : accounts) {
			if (acc.getId() == id) {
				return acc;
			}
		}
		return null;
	}

	public void updateAccount(Account account) {
		List<Account> accounts = getAccounts();
		Account oldAccount = searchAccount(account.getId());
		accounts.remove(oldAccount);
		accounts.add(account);
		accs.setAccounts(accounts);
		FileGenerator generator = new FileGenerator("account.xml", accs);
		generator.setClassName(Accounts.class);
		FileUtil.writeToFile(generator);
	}

	public List<Account> getAccounts(String passportNumber, List<String> currencies){
		List<Account> accounts = getAccounts();
		List<Account> filtredAccounts = new ArrayList<Account>();
		for(Account account : accounts){
			if(account.getPerson().getPassportNumber().equals(passportNumber) 
					&& ( account.getCurr().getShortName().equals(currencies.get(0)) || account.getCurr().getShortName().equals(currencies.get(0)))){
				filtredAccounts.add(account);
			}
		}
		return filtredAccounts;
	}
	
	public Account getBankAccount(String currency, String passportNumber){
		List<Account> accounts = getAccounts();
		for(Account account : accounts){
			if(account.getPerson().getPassportNumber().equals(passportNumber) && account.getCurr().getShortName().equals(currency)){
				return account;
			}
		}
		return null;
	}
}
