package com.epam.decorator;

import java.awt.Color;

public abstract class Decorator implements InterfaceComponent {
	protected InterfaceComponent component;

	public Decorator(InterfaceComponent c) {
		component = c;
	}

	public Color getHoverButtonColor() {
		return component.getHoverButtonColor();
	}

	public Color getExitButtonColor() {
		return component.getExitButtonColor();
	}

}
