package com.epam.factory.method;

import com.epam.model.DatabaseResource;
import com.epam.model.FileResource;
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
	
	public static ResourceFactoryMethod getFactoryMethod(Resource resource){
		if( resource instanceof FileResource){
			return new FileFactoryMethod();
		}
		if(resource instanceof DatabaseResource){
			return new DatabaseFactoryMethod();
		}
		return null;
	}
	
	public abstract void writePerson(Person person);
	public abstract Person readPerson();
	public abstract Person readPerson(String name);
}
