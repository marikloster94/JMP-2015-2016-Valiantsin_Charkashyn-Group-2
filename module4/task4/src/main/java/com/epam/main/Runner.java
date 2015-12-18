package com.epam.main;

import com.epam.decorator.Button;
import com.epam.decorator.Decorator;
import com.epam.decorator.RedOrangeDecorator;
import com.epam.window.Window;

public class Runner {

	public static void main(String[] args) {
		Decorator commonbutton = new RedOrangeDecorator(new Button());
		new Window(commonbutton.getHoverButtonColor(), commonbutton.getExitButtonColor()).showWindow();;
		new Window().showWindow();
	}

}
