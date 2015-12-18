package com.epam.window;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Window extends JFrame implements MouseListener {

	private ButtonComponent button;

	public Window() {
		button = new ButtonComponent();
	}

	public Window(Color hover, Color exit) {
		this.button = new ButtonComponent(hover, exit);

	}

	public void showWindow() {
		this.button.getButton().addMouseListener(this);
		add(this.button.getButton());
		setSize(200, 150);
		setTitle("JButton Color On Press");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent arg0) {
		button.getButton().setBackground(button.getColorHoverButton());
	}

	public void mouseExited(MouseEvent arg0) {
		button.getButton().setBackground(button.getColorExitButton());
	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}

}
