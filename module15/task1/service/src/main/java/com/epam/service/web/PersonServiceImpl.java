package com.epam.service.web;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.epam.service.dao.FileLoader;
import com.epam.service.dao.FileWriter;
import com.epam.service.dao.Loader;
import com.epam.service.dao.Writer;
import com.epam.service.jaxb.model.Persons;
import com.epam.service.model.Person;

public class PersonServiceImpl implements PersonService {

	private Loader loader = new FileLoader();
	private Writer writer = new FileWriter();
	private static Logger log = Logger.getLogger(PersonService.class);
	
	public Response getPerson(String login) {
		Object obj = null;
		try {
			obj = loader.load("person.xml", Persons.class);
		} catch (Exception e) {
			log.error(e);
		}
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		List<Person> persons = ((Persons) obj).getPersons();
		for (Person person : persons) {
			if (login.equals(person.getLogin())) {
				return Response.ok(person).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	public Response getAll() {
		Object obj = null;
		try {
			obj = loader.load("person.xml", Persons.class);
		} catch (Exception e) {
			log.error(e);
		}
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		List<Person> persons = ((Persons) obj).getPersons();
		GenericEntity<List<Person>> wrapper = new GenericEntity<List<Person>>(persons) {};
		return Response.ok(wrapper).build();
	}

	public Response addPerson(Person person) {
		Object obj = null;
		try {
			obj = loader.load("person.xml", Persons.class);
		} catch (Exception e) {
			log.error(e);
		}
		List<Person> persons = null;
		if (obj == null) {
			persons = new ArrayList<Person>();
		}
		persons = ((Persons) obj).getPersons();
		if(persons.contains(person) ){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		persons.add(person);
		Persons pers = new Persons();
		pers.setPersons(persons);
		try {
			writer.write("person.xml", pers, Persons.class);
		}
		catch (Exception e) {
			log.error(e);
			return Response.status(Response.Status.CONFLICT).build();
		}
		return Response.ok(person).build();
	}

	public Response deletePerson(String login) {
		Object obj = null;
		try {
			obj = loader.load("person.xml", Persons.class);
		} catch (Exception e) {
			log.error(e);
		}
		if (obj == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		List<Person> persons = ((Persons) obj).getPersons();
		List<Person> found = new ArrayList<Person>();
		for (Person per : persons) {
			if (login.equals(per.getLogin())) {
				found.add(per);
			}
		}
		persons.removeAll(found);
		Persons pers = new Persons();
		pers.setPersons(persons);
		try {
			writer.write("person.xml", pers, Persons.class);
		}
		catch (Exception e) {
			log.error(e);
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}

	public Response updatePerson(Person person) {
		Object obj = null;
		try {
			obj = loader.load("person.xml", Persons.class);
		} catch (Exception e) {
			log.error(e);
		}
		
		if (obj == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		List<Person> persons = ((Persons) obj).getPersons();
		List<Person> found = new ArrayList<Person>();
		for (Person per : persons) {
			if (person.getLogin().equals(per.getLogin())) {
				found.add(per);
			}
		}
		persons.removeAll(found);
		persons.add(person);
		Persons pers = new Persons();
		pers.setPersons(persons);
		try {
			writer.write("person.xml", pers, Persons.class);
		}
		catch (Exception e) {
			log.error(e);
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}

}
