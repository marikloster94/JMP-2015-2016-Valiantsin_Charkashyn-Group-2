package com.epam.module.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.module.jpa.entity.Personal;

@Component
public class PersonalDAO {

	@PersistenceContext
	private EntityManager em;
	
	public Personal save(Personal info) {
		if (info == null) {
			throw new IllegalArgumentException("Personal information can not be null");
		}
		info = em.merge(info);
		return info;
	}

	public void delete(Personal info) {
		em.remove(info);
	}
	
	public Personal get(int id) throws NoResultException {
		return em.find(Personal.class, id);
	}
}
