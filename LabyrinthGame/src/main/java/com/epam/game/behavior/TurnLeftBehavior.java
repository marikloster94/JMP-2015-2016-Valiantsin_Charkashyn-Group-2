package com.epam.game.behavior;

import com.epam.game.model.Duck;
import com.epam.game.model.Labyrinth;
import com.epam.game.model.Orientation;

public class TurnLeftBehavior implements Behavior {

	public Integer[] move(Labyrinth labyrinth, Integer[] coordinates, Duck duck) {
		switch (duck.getOrientation()) {
		case RIGHT:
			coordinates[0] -= 1;
			duck.setOrientation(Orientation.UP);
			break;
		case LEFT:
			coordinates[0] += 1;
			duck.setOrientation(Orientation.DOWN);
			break;
		case UP:
			coordinates[1] -= 1;
			duck.setOrientation(Orientation.LEFT);
			break;
		case DOWN:
			coordinates[1] += 1;
			duck.setOrientation(Orientation.RIGHT);
			break;
		}
		labyrinth.getSquare()[coordinates[0]][coordinates[1]] = "1";
		return coordinates;

	}

	public boolean acceptWithHightestPriority(Labyrinth labyrinth, int x, int y, Orientation orientation) {
		switch (orientation) {
		case RIGHT:
			if ("0".equals(labyrinth.getSquare()[x - 1][y]) || "e".equals(labyrinth.getSquare()[x - 1][y])) {
				return true;
			}
			break;
		case LEFT:
			if ("0".equals(labyrinth.getSquare()[x + 1][y]) || "e".equals(labyrinth.getSquare()[x + 1][y])) {
				return true;
			}
			break;
		case UP:
			if ("0".equals(labyrinth.getSquare()[x][y - 1]) || "e".equals(labyrinth.getSquare()[x][y - 1])) {
				return true;
			}
			break;
		case DOWN:
			if ("0".equals(labyrinth.getSquare()[x][y + 1]) || "e".equals(labyrinth.getSquare()[x][y + 1])) {
				return true;
			}
			break;
		}
		return false;
	}

	@Override
	public boolean acceptWithLowestPriority(Labyrinth labyrinth, int x, int y, Orientation orientation) {
		switch (orientation) {
		case RIGHT:
			if ("1".equals(labyrinth.getSquare()[x - 1][y]) || "e".equals(labyrinth.getSquare()[x - 1][y])) {
				return true;
			}
			break;
		case LEFT:
			if ("1".equals(labyrinth.getSquare()[x + 1][y]) || "e".equals(labyrinth.getSquare()[x + 1][y])) {
				return true;
			}
			break;
		case UP:
			if ("1".equals(labyrinth.getSquare()[x][y - 1]) || "e".equals(labyrinth.getSquare()[x][y - 1])) {
				return true;
			}
			break;
		case DOWN:
			if ("1".equals(labyrinth.getSquare()[x][y + 1]) || "e".equals(labyrinth.getSquare()[x][y + 1])) {
				return true;
			}
			break;
		}
		return false;
	}

}
