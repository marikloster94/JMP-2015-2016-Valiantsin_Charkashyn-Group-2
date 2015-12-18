package com.epam.decorator;

import java.awt.Color;

public class RedOrangeDecorator extends Decorator {

	public RedOrangeDecorator(InterfaceComponent c) {
		super(c);
	}
	
	public Color getHoverButtonColor() {
		return Color.RED;
	}

	public Color getExitButtonColor() {
		return Color.ORANGE;
	}

}
