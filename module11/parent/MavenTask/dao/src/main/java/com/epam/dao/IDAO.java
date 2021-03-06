package com.epam.dao;

import java.sql.SQLException;

import com.epam.exception.HsqlDBException;

public interface IDAO {

	void create(Object obj) throws HsqlDBException;
	Object get(int id) throws SQLException;
	void update(Object obj) throws SQLException;
	void delete(int id) throws HsqlDBException;
	int getLastId() throws SQLException;
	
}
