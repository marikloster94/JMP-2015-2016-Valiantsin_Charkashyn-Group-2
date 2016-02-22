package com.epam.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.dao.AccountDAO;
import com.epam.exception.ElementNotFoundException;
import com.epam.exception.HsqlDBException;
import com.epam.model.Account;
import com.epam.model.Person;

public class AccountService {

	private static final AccountDAO dao = new AccountDAO();

	public List<Account> getAccounts() throws SQLException {
		return dao.getAll();
	}

	public void addAccount(Account acc) throws HsqlDBException {
		dao.create(acc);
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
		account.setPerson(person);
		updateAccount(account);
	}

	public Account searchAccount(int id) throws SQLException {
		return (Account) dao.get(id);
	}

	public void updateAccount(Account account) throws SQLException {
		dao.update(account);
	}

	public List<Account> getAccounts(String passportNumber,
			List<String> currencies) throws SQLException {
		List<Account> accounts = getAccounts();
		List<Account> filtredAccounts = new ArrayList<Account>();
		for (Account account : accounts) {
			if (account.getPerson().getPassportNumber().equals(passportNumber)
					&& (account.getCurr().getShortName()
							.equals(currencies.get(0)) || account.getCurr()
							.getShortName().equals(currencies.get(0)))) {
				filtredAccounts.add(account);
			}
		}
		return filtredAccounts;
	}

	public Account getBankAccount(String currency, String passportNumber)
			throws SQLException {
		List<Account> accounts = getAccounts();
		for (Account account : accounts) {
			if (account.getPerson().getPassportNumber().equals(passportNumber)
					&& account.getCurr().getShortName().equals(currency)) {
				return account;
			}
		}
		return null;
	}
}
