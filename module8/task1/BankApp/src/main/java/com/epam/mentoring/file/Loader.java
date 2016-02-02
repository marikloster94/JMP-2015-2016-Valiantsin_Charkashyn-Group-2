package com.epam.mentoring.file;

import java.util.List;

import com.epam.mentoring.exception.FileException;

public interface Loader {
	List<String> load(String filename) throws FileException;
}
