package com.epam.module.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.epam.module.jpa.entity.Project;
import com.epam.module.jpa.entity.Unit;

@Component
public class ProjectDAO {

	@PersistenceContext
	private EntityManager em;
	
	public Project save(Project project) {
		if (project == null) {
			throw new IllegalArgumentException("Project can not be null");
		}
		project = em.merge(project);
		return project;
	}

	public void delete(Project project) {
		em.remove(project);
	}

	public List<Project> getAll() throws NoResultException {
		return em.createQuery("SELECT p FROM Project p").getResultList();
	}

	public Project get(int id) throws NoResultException {
		return em.find(Project.class, id);
	}

	public Project update(Project project) {
		if (project == null) {
			throw new IllegalArgumentException("Project can not be null");
		}
		em.merge(project);
		return project;
	}
	
	
}
