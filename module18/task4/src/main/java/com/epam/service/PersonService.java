package com.epam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.dao.PersonDAO;
import com.epam.model.Person;

@Repository
public class PersonService {

	@Autowired
	PersonDAO dao;
	
	
	@Transactional
	public Person addPerson(Person searchPerson) throws Exception {
		return dao.add(searchPerson);
	}
	
	public List<Person> getAll() throws Exception{
		return dao.getAll();
	}
}
