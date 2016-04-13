package com.epam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.model.Person;

@Component
public class PersonDAO {

	@PersistenceContext
	private EntityManager em;
	
	public Person add(Person person) throws Exception {
		if (person == null) {
			throw new IllegalArgumentException("Person can not be null");
		}
		return em.merge(person);
	}
	
	public List<Person> getAll() throws Exception {
		return em.createQuery("SELECT p from Person p").getResultList();
	}
	
	public Person get(int id) throws Exception {
		return em.find(Person.class, id);
	}
}
