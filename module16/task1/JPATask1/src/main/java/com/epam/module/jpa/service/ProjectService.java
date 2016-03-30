package com.epam.module.jpa.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.module.jpa.dao.ProjectDAO;
import com.epam.module.jpa.entity.Employee;
import com.epam.module.jpa.entity.Project;

@Component
public class ProjectService {

	@Autowired
	ProjectDAO dao;
	
	@Transactional
	public Project add(Project project) {
		if (dao == null) {
			return null;
		}
		if (project == null) {
			return null;
		}
		return dao.save(project);
	}
	
	public Project get(int id){
		return dao.get(id);
	}

	@Transactional
	public void delete(Project project) {
		if (dao == null) {
			return;
		}
		if (project == null) {
			return;
		}
		try {
			Project finded = dao.get(project.getProjectId());
			if (finded != null) {
				dao.delete(finded);
			}
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}

	}
	
	@Transactional
	public Project update(Project project) {
		if (dao == null) {
			return null;
		}
		if (project == null) {
			return null;
		}
		Project finded = null;
		try {
			finded = dao.get(project.getProjectId());
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		if (finded == null) {
			return null;
		}
		return dao.update(project);
	}
	
	public void addEmployeeToProject(Employee employee, int projectId){
		if (dao == null) {
			return ;
		}
		Project finded = null;
		try {
			finded = dao.get(projectId);
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		if (finded == null) {
			return ;
		}
		List<Employee> employees = finded.getEmployees();
		if( employees != null) {
			employees.add(employee);
			finded.setEmployees(employees);
			update(finded);
		}
	}
}
