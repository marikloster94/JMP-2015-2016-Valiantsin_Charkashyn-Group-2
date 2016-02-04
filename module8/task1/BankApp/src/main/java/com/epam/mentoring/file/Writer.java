package com.epam.mentoring.file;

import com.epam.mentoring.exception.FileException;

public interface Writer {
	void write(String filename, Object data, Class<?> className) throws FileException;
}
