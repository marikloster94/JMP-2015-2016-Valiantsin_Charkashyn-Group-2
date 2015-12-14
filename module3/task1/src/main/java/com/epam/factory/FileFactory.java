package com.epam.factory;

import java.io.File;

import com.epam.model.FileResource;
import com.epam.model.Resource;
import com.epam.property.PropertyReader;

public class FileFactory extends ResourceFactory {

	public FileFactory() {
		resource = new FileResource();
	}

	@Override
	public Resource createResource() {
		String resourceName = PropertyReader.readFromProperty("file.name");
		if (resourceName != null) {
			resource.serResourceName(resourceName);
			((FileResource) resource).setFile(new File(resourceName));
		}
		return resource;
	}

}
