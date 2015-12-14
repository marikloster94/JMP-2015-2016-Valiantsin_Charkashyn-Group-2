package com.epam.factory;

import java.io.File;

import com.epam.facade.ResourceFacade;
import com.epam.model.FileResource;
import com.epam.model.Resource;

public class FileFactory extends ResourceFactory {

	public FileFactory() {
		resource = new FileResource();
	}

	@Override
	public Resource createResource() {
		String resourceName = ResourceFacade.readFromProperty("file.name");
		if (resourceName != null) {
			resource.serResourceName(resourceName);
			((FileResource) resource).setFile(new File(resourceName));
		}
		return (FileResource) resource;
	}

}
