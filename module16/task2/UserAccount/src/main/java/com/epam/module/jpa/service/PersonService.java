package com.epam.module.jpa.service;

import java.util.List;

import javax.persistence.NoResultException;
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
	public Person add(Person person) {
		if (dao == null) {
			return null;
		}
		person = dao.save(person);

		return person;
	}

	@Transactional
	public void delete(Person person) {
		if (dao == null) {
			return;
		}
		try {
			Person finded = dao.get(person.getPersonID());
			if (finded != null) {
				dao.delete(finded);
			}
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}

	}

	@Transactional
	public Person searchPerson(String passNumber) {
		if (dao == null || passNumber == null) {
			return null;
		}
		Person finded = null;
		try {
			finded = dao.getByPassport(passNumber);
			
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		return finded;

	}

	@Transactional
	public List<Person> getPeople() {
		List<Person> finded = null;
		try {
			finded = dao.getAll();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		return finded;
	}

}
