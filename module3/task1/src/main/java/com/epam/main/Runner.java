package com.epam.main;

import com.epam.facade.ResourceFacade;
import com.epam.factory.ResourceFactory;
import com.epam.factory.method.FileFactoryMethod;
import com.epam.factory.method.ResourceFactoryMethod;
import com.epam.model.Person;
import com.epam.model.Resource;

public class Runner {

	public static void main(String[] args) {
		ResourceFactory factory = ResourceFacade.chooseFactory();
		Resource res = factory.createResource();
		ResourceFactoryMethod method = FileFactoryMethod.getFactoryMethod(res);
		if (method != null) {
			method.setResource(res);
			Person person = ResourceFacade.createPerson();
			System.out.println("Write person to resource");
			method.writePerson(person);
			System.out.println("Read first person from resource");
			person = method.readPerson();
			ResourceFacade.printResult(person);
			System.out.println("Read person by name Alex from resource");
			person = method.readPerson("Alex");
			ResourceFacade.printResult(person);
			System.out.println("Read person by name Lama from resource");
			person = method.readPerson("Lama");
			ResourceFacade.printResult(person);
			ResourceFacade.closeResource(res);
		}

	}

}
