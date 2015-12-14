package com.epam.main;

import com.epam.factory.DatabaseFactory;
import com.epam.factory.ResourceFactory;
import com.epam.factory.method.DatabaseFactoryMethod;
import com.epam.model.DatabaseResource;
import com.epam.model.Person;

public class Runner {

	public static void main(String[] args) {
		ResourceFactory factory = new DatabaseFactory();
		DatabaseResource res = (DatabaseResource) factory.createResource();
		DatabaseFactoryMethod method = new DatabaseFactoryMethod();
		method.setResource(res);
		Person person = new Person();
		person.setName("mai");
		person.setSurname("kloster");
		person.setAge(18);
		method.writePerson(person);
		Person person1 = method.readPerson();
		System.out.println(person1);
		Person person2 = method.readPerson("mai");
		System.out.println(person2);
//		System.out.println(res.getConnection() == null);
//		FileFactory file = new FileFactory();
//		FileResource files = (FileResource) file.createResource();
//		FileFactoryMethod method = new FileFactoryMethod();
//		method.setResource(files);
//		Person person = new Person();
//		person.setName("maria");
//		person.setSurname("kloster");
//		person.setAge(21);
//		method.writePerson(person);
//		Person obj = method.readPerson("maria");
//		System.out.println(obj);
	}

}
