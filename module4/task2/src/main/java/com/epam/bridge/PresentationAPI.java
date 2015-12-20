package com.epam.bridge;

import java.util.List;

import javax.swing.JFrame;

public interface PresentationAPI {

	@SuppressWarnings("rawtypes")
	public void show(List list,  JFrame frame);
}
