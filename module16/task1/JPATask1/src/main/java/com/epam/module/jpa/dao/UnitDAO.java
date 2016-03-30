package com.epam.module.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.module.jpa.entity.Unit;

@Component
public class UnitDAO {

	@PersistenceContext
	private EntityManager em;
	
	public Unit save(Unit unit) {
		if (unit == null) {
			throw new IllegalArgumentException("Unit can not be null");
		}
		return em.merge(unit);
	}

	public void delete(Unit unit) {
		em.remove(unit);
	}

	public List<Unit> getAll() throws NoResultException {
		return em.createQuery("SELECT u FROM Unit u").getResultList();
	}

	public Unit get(int id) throws NoResultException {
		return em.find(Unit.class, id);
	}

	public Unit update(Unit unit) {
		if (unit == null) {
			throw new IllegalArgumentException("Unit can not be null");
		}
		em.merge(unit);
		return unit;
	}
}
