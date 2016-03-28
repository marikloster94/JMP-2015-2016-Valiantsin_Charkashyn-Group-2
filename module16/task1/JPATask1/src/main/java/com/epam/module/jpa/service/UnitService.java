package com.epam.module.jpa.service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.module.jpa.dao.UnitDAO;
import com.epam.module.jpa.entity.Employee;
import com.epam.module.jpa.entity.Unit;

@Component
public class UnitService {

	@Autowired
	UnitDAO dao;

	@Transactional
	public Unit add(Unit unit) {
		if (dao == null) {
			return null;
		}
		if(unit == null) {
			return null;
		}
		return dao.save(unit);
	}

	@Transactional
	public void delete(Unit unit) {
		if (dao == null) {
			return;
		}
		if(unit == null){
			return;
		}
		try {
			Unit finded = dao.get(unit.getUnitId());
			if (finded != null) {
				dao.delete(finded);
			}
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}

	}

	public Unit get(int id) {
		if (dao == null) {
			return null;
		}
		Unit finded = null;
		try {
			finded = dao.get(id);

		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		return finded;
	}
	
	@Transactional
	public Unit update(Unit unit) {
		if (dao == null) {
			return null;
		}
		if (unit == null) {
			return null;
		}
		Unit finded = null;
		try {
			finded = dao.get(unit.getUnitId());
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		if (finded == null) {
			return null;
		}
		return dao.update(unit);
	}
	
	
}
