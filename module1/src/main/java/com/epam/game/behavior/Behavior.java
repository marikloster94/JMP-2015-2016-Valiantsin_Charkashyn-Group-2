package com.epam.game.behavior;

import com.epam.game.model.Duck;
import com.epam.game.model.Labyrinth;

public interface Behavior extends ParamAccepter {

	Integer[] move(Labyrinth labyrinth , Integer [] coordinates, Duck duck);
	
}
