package com.epam.game.behavior;

import com.epam.game.model.Labyrinth;

public interface Behavior {

	void move(Labyrinth labyrinth , int [] coordinates);
}
