package com.epam.game.behavior;

import com.epam.game.model.Labyrinth;
import com.epam.game.model.Orientation;

public interface ParamAccepter {
	boolean acceptWithLowestPriority(Labyrinth labyrinth, int x, int y, Orientation orientaion);
	
	boolean acceptWithHightestPriority(Labyrinth labyrinth, int x, int y, Orientation orientation);
}
