package com.epam.module.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.module.jpa.dao.PersonDAO;
import com.epam.module.jpa.entity.Person;
@Component
public class PersonService {

	@Autowired
	PersonDAO dao;
	
	@Transactional
	public Person add(Person person){
		if( dao == null){
			return null;
		}
		person = dao.save(person);
		
		return person;
	}
	
	@Transactional
	public void delete(Person person){
		if( dao == null){
			return;
		}
		Person finded = dao.get(person.getPersonID());
		if(finded != null){
			dao.delete(finded);
		}
	}
	
	@Transactional
	public Person searchPerson(String passNumber){
		if( dao == null || passNumber == null){
			return null;
		}
		return dao.getByPassport(passNumber);
		
	}
	
	@Transactional
	public List<Person> getPeople(){
		return dao.getAll();
	}
	
	
}
