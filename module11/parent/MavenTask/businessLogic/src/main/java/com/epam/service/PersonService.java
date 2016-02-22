package com.epam.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.dao.PersonDAO;
import com.epam.exception.AddNewElementException;
import com.epam.model.Person;

public class PersonService {

	private static final PersonDAO dao = new PersonDAO();

	public List<Person> getPersons() throws SQLException {
		return dao.getAll();
	}

	public Person searchPerson(String passportNumber) throws SQLException {
		return (Person) dao.get(passportNumber);
	}

	public void addPerson(Person searchPerson) throws Exception {
		Person person = searchPerson(searchPerson.getPassportNumber());
		if (person == null) {
			searchPerson.setId(dao.getLastId());
			dao.create(searchPerson);
		} else {
			throw new AddNewElementException(
					"You can't add new person cause he exist");
		}
	}

	public Person getBankPerson() throws SQLException {
		return searchPerson("MC2345674");
	}
}
