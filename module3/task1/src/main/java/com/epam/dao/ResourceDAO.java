package com.epam.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.model.Person;

public interface ResourceDAO {

	void addPerson(Connection connection, Person person) throws SQLException;
	byte[] getFirstPerson(Connection connection) throws SQLException;
	List<byte[]> getPersons(Connection connection) throws SQLException;
}
