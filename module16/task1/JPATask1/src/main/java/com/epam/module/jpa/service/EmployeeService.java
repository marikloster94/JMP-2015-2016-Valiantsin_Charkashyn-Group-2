package com.epam.module.jpa.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.module.jpa.dao.EmployeeDAO;
import com.epam.module.jpa.entity.Employee;

@Component
public class EmployeeService {

	@Autowired
	EmployeeDAO dao;

	@Transactional
	public Employee add(Employee person) {
		if (dao == null) {
			return null;
		}
		person = dao.save(person);

		return person;
	}

	@Transactional
	public void delete(Employee person) {
		if (dao == null) {
			return;
		}
		try {
			Employee finded = dao.get(person.getEmployeeID());
			if (finded != null) {
				dao.delete(finded);
			}
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}

	}

	@Transactional
	public Employee searchPerson(String passNumber) {
		if (dao == null || passNumber == null) {
			return null;
		}
		Employee finded = null;
		try {
			finded = dao.getByPassport(passNumber);
			
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		return finded;

	}

	@Transactional
	public List<Employee> getPeople() {
		List<Employee> finded = null;
		try {
			finded = dao.getAll();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		return finded;
	}

}
