package com.epam.window;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class FlyweightWindow {

	private JFrame frame;
	private JPanel gui;
	private JScrollPane panel;

	public FlyweightWindow(JScrollPane panel) {
		this.setPanel(panel);
		this.frame = new JFrame("Flyweight");
		this.gui = new JPanel(new BorderLayout());
		gui.add(panel, BorderLayout.CENTER);
		gui.setBorder(new EmptyBorder(3, 3, 3, 3));
		frame.setContentPane(gui);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public JScrollPane getPanel() {
		return panel;
	}

	public void setPanel(JScrollPane panel) {
		this.panel = panel;
	}
}
