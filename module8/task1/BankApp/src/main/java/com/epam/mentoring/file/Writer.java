package com.epam.mentoring.file;

import java.util.List;

import com.epam.mentoring.exception.FileException;

public interface Writer {
	void write(String filename, List data) throws FileException;
}
