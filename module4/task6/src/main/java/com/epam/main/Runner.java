package com.epam.main;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import com.epam.factory.DirectoryListFactory;
import com.epam.flyweight.FlyWeight;
import com.epam.model.DirectoryList;
import com.epam.window.FlyweightWindow;

public class Runner {

	public static void main(String[] args) {
		FlyWeight flyweight = new FlyWeight(new DirectoryListFactory());
		flyweight.setDirectoryList(flyweight.getFactory().getGui(FlyWeight.getDiretory()));
		JScrollPane panel = new JScrollPane(((DirectoryList)flyweight.getDirectoryList()).getDirectoryFiles());
		panel.setPreferredSize(new Dimension(600, 600));
		new FlyweightWindow(panel);

	}

}
