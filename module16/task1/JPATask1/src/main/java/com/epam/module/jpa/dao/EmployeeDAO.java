package com.epam.module.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.module.jpa.entity.Employee;

@Component
public class EmployeeDAO {

	@PersistenceContext
	private EntityManager em;

	public Employee save(Employee person) {
		if (person == null) {
			throw new IllegalArgumentException("Person can not be null");
		}
		person = em.merge(person);
		return person;
	}

	public void delete(Employee person) {
		em.remove(person);
	}

	public List<Employee> getAll() throws NoResultException {
		return em.createQuery("SELECT p FROM Person p").getResultList();
	}

	public Employee getByPassport(String passNumber) throws NoResultException {
		return em.createQuery("SELECT p FROM Person p WHERE p.passNumber = :passport",Employee.class).setParameter("passport", passNumber)
				.getSingleResult();
	}

	public Employee get(int id) throws NoResultException {
		return em.find(Employee.class, id);
	}

	public Employee update(Employee person) {
		if (person == null) {
			throw new IllegalArgumentException("Person can not be null");
		}
		em.merge(person);
		return person;
	}
}
