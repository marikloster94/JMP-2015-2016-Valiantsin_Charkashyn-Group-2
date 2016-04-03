package com.epam.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import com.epam.dao.AccountDAO;
import com.epam.exception.ElementNotFoundException;
import com.epam.model.Account;
import com.epam.model.Person;

@Repository
@Configuration
public class AccountService {

	@Autowired
	private AccountDAO dao;

	public List<Account> getAccounts() throws Exception {
		return dao.getAll();
	}

	@Transactional
	public Account addAccount(Account acc) throws Exception {
		return dao.create(acc);
	}
	@Transactional
	public void assignPerson(Person person, Account account) throws Exception {
		if (account == null) {
			throw new ElementNotFoundException(
					"You can't assign person to nonexistent account");
		}
		if (person == null) {
			throw new ElementNotFoundException(
					"You can't assign nonexistent person to account");
		}
		account.setMan(person);
		updateAccount(account);
	}

	public Account searchAccount(int id) throws Exception {
		return (Account) dao.get(id);
	}
	@Transactional
	public Account updateAccount(Account account) throws Exception {
		return dao.update(account);
	}

	public List<Account> getAccounts(String passportNumber,
			List<String> currencies) throws Exception {
		List<Account> accounts = getAccounts();
		List<Account> filtredAccounts = new ArrayList<Account>();
		for (Account account : accounts) {
			if (account.getMan().getPassportNumber().equals(passportNumber)
					&& (account.getCurr().getShortName()
							.equals(currencies.get(0)) || account.getCurr()
							.getShortName().equals(currencies.get(0)))) {
				filtredAccounts.add(account);
			}
		}
		return filtredAccounts;
	}

	public Account getBankAccount(String currency, String passportNumber)
			throws Exception {
		List<Account> accounts = getAccounts();
		for (Account account : accounts) {
			if (account.getMan().getPassportNumber().equals(passportNumber)
					&& account.getCurr().getShortName().equals(currency)) {
				return account;
			}
		}
		return null;
	}
}
