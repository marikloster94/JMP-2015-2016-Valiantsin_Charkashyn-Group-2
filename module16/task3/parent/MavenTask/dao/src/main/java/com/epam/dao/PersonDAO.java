package com.epam.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.exception.HsqlDBException;
import com.epam.model.Person;

@Component
public class PersonDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public Person create(Person person) throws Exception {
		if (person == null) {
			throw new IllegalArgumentException("Person can not be null");
		}
		try {
			person = em.merge(person);
		} catch (Exception ex) {
			throw new HsqlDBException("Cannot add person to db", ex);
		}
		return person;

	}

	public List<Person> getAll() throws Exception {
		return em.createQuery("SELECT p from Person p").getResultList();

	}

	public Person get(int id) throws SQLException {
		return em.find(Person.class, id);
	}

	public Object get(String passportNumber) throws SQLException {
		return em.createQuery("SELECT p FROM Person p WHERE p.passportNumber = :passport", Person.class).setParameter("passport", passportNumber)
				.getSingleResult();
	}


}
