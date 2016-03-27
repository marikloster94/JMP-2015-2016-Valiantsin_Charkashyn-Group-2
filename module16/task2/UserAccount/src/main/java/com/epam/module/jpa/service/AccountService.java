package com.epam.module.jpa.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.module.jpa.dao.AccountDAO;
import com.epam.module.jpa.entity.Account;

@Component
public class AccountService {

	@Autowired
	AccountDAO dao;
	
	@Transactional
	public Account add(Account acc){
		if( dao == null){
			return null;
		}
		acc = dao.save(acc);
		return acc;
	}

	@Transactional
	public Account update(Account acc){
		if( dao == null){
			return null;
		}
		acc = dao.update(acc);
		return acc;
	}
	@Transactional
	public void delete(Account acc){
		if( dao == null){
			return;
		}
		Account findedAcc = dao.get(acc.getAccountID());
		if(findedAcc != null){
			dao.delete(findedAcc);
		}
	}
}
