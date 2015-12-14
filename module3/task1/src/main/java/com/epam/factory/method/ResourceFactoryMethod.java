package com.epam.factory.method;

import com.epam.model.Person;
import com.epam.model.Resource;

public abstract class ResourceFactoryMethod {

	protected Resource resource;
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	} 
	
	public abstract void writePerson(Person person);
	public abstract Person readPerson();
	public abstract Person readPerson(String name);
}
