package com.epam.module.jpa.service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.module.jpa.dao.PersonalDAO;
import com.epam.module.jpa.entity.Personal;

@Component
public class PersonalService {

	@Autowired
	PersonalDAO dao;
	
	@Transactional
	public Personal add(Personal info) {
		if (dao == null) {
			return null;
		}
		if (info == null) {
			return null;
		}
		return dao.save(info);
	}

	@Transactional
	public void delete(Personal info) {
		if (dao == null) {
			return;
		}
		if (info == null) {
			return;
		}
		try {
			Personal finded = dao.get(info.getId());
			if (finded != null) {
				dao.delete(finded);
			}
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}

	}
}
