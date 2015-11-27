package com.epam.game.algorithm;

import com.epam.game.model.Duck;
import com.epam.game.model.Labyrinth;

public interface PassingAlgorithm {
	public void pass(Labyrinth labyrinth, int[] coordinates, Duck duck);
}
