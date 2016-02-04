package com.epam.mentoring.file;

import com.epam.mentoring.exception.FileException;

public interface Loader {
	Object load(String filename, Class<?> className) throws FileException;
}
