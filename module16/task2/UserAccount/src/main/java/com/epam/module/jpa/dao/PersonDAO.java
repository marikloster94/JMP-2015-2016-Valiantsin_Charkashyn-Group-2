package com.epam.module.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.module.jpa.entity.Person;


@Component
public class PersonDAO {

    @PersistenceContext
    private EntityManager em;
    
    public Person save(Person person) {
		if (person == null) {
			throw new IllegalArgumentException("Person can not be null");
		}
		person = em.merge(person);
		return person;
	}
	
	public void delete(Person person) {
		em.remove(person);
	}
	
	public List<Person> getAll(){
		return em.createQuery("SELECT p FROM Person p").getResultList();
	}
	
	public Person getByPassport(String passNumber){
		return em.createQuery("SELECT p FROM Person p WHERE p.passNumber = :passport", Person.class).setParameter("passport", passNumber).getSingleResult();
	}
	
	public Person get(int id){
		return em.find(Person.class, id);
	}
}
