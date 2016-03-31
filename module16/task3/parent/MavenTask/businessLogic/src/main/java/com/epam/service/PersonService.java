package com.epam.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.dao.PersonDAO;
import com.epam.exception.AddNewElementException;
import com.epam.model.Person;

@Repository
public class PersonService {

	@Autowired
	private PersonDAO dao;

	public List<Person> getPersons() throws Exception {
		return dao.getAll();
	}

	public Person searchPerson(String passportNumber) throws Exception {
		return (Person) dao.get(passportNumber);
	}

	public Person addPerson(Person searchPerson) throws Exception {
		Person person = searchPerson(searchPerson.getPassportNumber());
		if (person == null) {
			person = dao.create(searchPerson);
		} else {
			throw new AddNewElementException("You can't add existing person ");
		}
		return person;
	}

	public Person getBankPerson() throws Exception {
		return searchPerson("MC2345674");
	}
}
