package com.epam.mentoring.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.epam.mentoring.file.ClientThread;
import com.epam.mentoring.file.FileGenerator;
import com.epam.mentoring.jaxb.model.Accounts;
import com.epam.mentoring.model.Account;
import com.epam.mentoring.util.FileUtil;

public class AccountService {
	private final ClientThread client = new ClientThread("account.xml");

	public AccountService() {
		client.setClassName(Accounts.class);
	}
	
	public List<Account> getAccounts(){
		return ((Accounts) FileUtil.loadFromFile(client)).getAccounts();
	}
	
	public int getLastId(){
		List<Account> accounts = getAccounts();
		if(accounts == null){
			return 1;
		}
		Collections.sort(accounts, new Comparator<Account>() {
			@Override
			public int compare(Account o1, Account o2) {
				return o2.getId() - o1.getId();
			}
		});
		return accounts.get(0).getId()+1;
	}
	
	public void addAccount(Account acc){
		List<Account> accounts = getAccounts();
		accounts.add(acc);
		Accounts accs= new Accounts();
		accs.setAccounts(accounts);
		FileUtil.writeToFile(new FileGenerator("account.xml", accs));
	}
}
