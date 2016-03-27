package com.epam.module.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.module.jpa.entity.Account;


@Component
public class AccountDAO {

	@PersistenceContext
	private EntityManager em;

	public Account save(Account account) {
		if (account == null) {
			throw new IllegalArgumentException("Account can not be null");
		}
		
		return em.merge(account);
	}
	
	public void delete(Account account) {
		 em.remove(account);
        
	}
	
	public Account update(Account account){
		if (account == null) {
			throw new IllegalArgumentException("Account can not be null");
		}
		return em.merge(account);
	}
	
	public Account get(int id) throws NoResultException{
		Account findedAcc = em.find(Account.class, id);
		return findedAcc;
	}
    

}
