package com.epam.factory;

import com.epam.model.Resource;

public abstract class ResourceFactory {

	protected Resource resource;
	
	public ResourceFactory() {
	}
	
	public abstract Resource createResource();
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
