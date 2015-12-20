package com.epam.bridge;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class JListPresentationAPI implements PresentationAPI {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void show(List list, JFrame frame) {
		frame.setLayout(new BorderLayout());
		DefaultListModel model = new DefaultListModel();
		JList jList = new JList(model);
		JScrollPane pane = new JScrollPane(jList);
		for (Object obj:list){
			model.addElement(obj.toString());
		}
		frame.add(pane, BorderLayout.NORTH);

	}
}
