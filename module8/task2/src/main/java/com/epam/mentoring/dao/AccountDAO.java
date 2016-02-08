package com.epam.mentoring.dao;

import java.sql.SQLException;
import java.util.List;

import com.epam.mentoring.exception.HsqlDBException;
import com.epam.mentoring.model.Account;

public class AccountDAO implements IDAO {

	@Override
	public void create(Object obj) throws HsqlDBException {
		Account acc = (Account) obj;
		acc.setId(getLastId());

	}

	@Override
	public Object get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object obj) throws HsqlDBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws HsqlDBException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLastId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<Account> getAll(){
		return null;
	}

}
