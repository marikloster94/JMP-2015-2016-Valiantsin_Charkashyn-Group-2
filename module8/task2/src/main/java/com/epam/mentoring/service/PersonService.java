package com.epam.mentoring.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.mentoring.dao.IDAO;
import com.epam.mentoring.dao.PersonDAO;
import com.epam.mentoring.exception.AddNewElementException;
import com.epam.mentoring.model.Person;

public class PersonService {
	
	private static final IDAO dao = new PersonDAO();	
	
	public List<Person> getPersons() throws SQLException{
		return ((PersonDAO)dao).getAll();
	}

	public Person searchPerson(String passportNumber) throws SQLException{
		return (Person) ((PersonDAO)dao).get(passportNumber);
	}
	
	public void addPerson(Person searchPerson) throws Exception{
		Person person = searchPerson(searchPerson.getPassportNumber());
		if(person == null){
			searchPerson.setId(dao.getLastId());
			dao.create(searchPerson);
		}else{
			throw new AddNewElementException("You can't add new person cause he exist");
		}
	}
	
	public Person getBankPerson() throws SQLException{
		return searchPerson("MC2345674");
	}
}
