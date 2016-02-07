package com.epam.mentoring.service;

import java.util.List;

import com.epam.mentoring.exception.AddNewElementException;
import com.epam.mentoring.file.ClientThread;
import com.epam.mentoring.file.FileGenerator;
import com.epam.mentoring.jaxb.model.Persons;
import com.epam.mentoring.model.Person;
import com.epam.mentoring.util.FileUtil;

public class PersonService {
	
	private final ClientThread client = new ClientThread("person.xml");	
	
	public PersonService(){
		client.setClassName(Persons.class);
	}
	
	public List<Person> getPersons(){
		return ((Persons)FileUtil.loadFromFile(client)).getClients();
	}

	public Person searchPerson(String passportNumber){
		List<Person> clients = getPersons();
		for(Person client:clients){
			if(passportNumber.equals(client.getPassportNumber())){
				return client;
			}
		}
		return null;
	}
	
	public void addPerson(Person searchPerson) throws Exception{
		Person person = searchPerson(searchPerson.getPassportNumber());
		if(person == null){
			List<Person> clients = ((Persons)FileUtil.loadFromFile(client)).getClients();
			clients.add(searchPerson);
			Persons persons = new Persons();
			persons.setPersons(clients);
			FileGenerator generator = new FileGenerator("person.xml", persons);
			generator.setClassName(Persons.class);
			FileUtil.writeToFile(generator);
		}else{
			throw new AddNewElementException("You can't add new person cause he exist");
		}
	}
	
	public Person getBankPerson(){
		return searchPerson("MC2467923");
	}
}
