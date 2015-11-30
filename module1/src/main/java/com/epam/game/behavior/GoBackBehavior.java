package com.epam.game.behavior;

import com.epam.game.model.Duck;
import com.epam.game.model.Labyrinth;
import com.epam.game.model.Orientation;

public class GoBackBehavior implements Behavior {

	public Integer[] move(Labyrinth labyrinth, Integer[] coordinates, Duck duck) {
		labyrinth.getSquare()[coordinates[0]][coordinates[1]] = "-1";
		switch (duck.getOrientation()) {
		case RIGHT:
			coordinates[1] -= 1;
			break;
		case LEFT:
			coordinates[1] += 1;
			break;
		case UP:
			coordinates[0] += 1;
			break;
		case DOWN:
			coordinates[0] -= 1;
			break;
		}
		return coordinates;
	}

	public boolean acceptWithHightestPriority(Labyrinth labyrinth, int x, int y, Orientation orientation) {
		switch (orientation) {
		case RIGHT:
			if (("*".equals(labyrinth.getSquare()[x][y + 1]) || "-1".equals(labyrinth.getSquare()[x][y + 1]) || "i".equals(labyrinth.getSquare()[x][y + 1]))
					&& ("*".equals(labyrinth.getSquare()[x + 1][y]) || "-1".equals(labyrinth.getSquare()[x + 1][y]) || "i".equals(labyrinth.getSquare()[x + 1][y]))
					&& ("*".equals(labyrinth.getSquare()[x - 1][y]) || "-1".equals(labyrinth.getSquare()[x - 1][y]) || "i".equals(labyrinth.getSquare()[x - 1][y]))
					&& "0".equals(labyrinth.getSquare()[x][y - 1])) {
				return true;
			}
			break;
		case LEFT:
			if (("*".equals(labyrinth.getSquare()[x + 1][y]) || "-1".equals(labyrinth.getSquare()[x + 1][y]) || "i".equals(labyrinth.getSquare()[x + 1][y]) )
					&& ("*".equals(labyrinth.getSquare()[x - 1][y]) || "-1".equals(labyrinth.getSquare()[x - 1][y]) || "i".equals(labyrinth.getSquare()[x - 1][y]))
					&& ("*".equals(labyrinth.getSquare()[x][y - 1]) || "-1".equals(labyrinth.getSquare()[x][y - 1]) || "i".equals(labyrinth.getSquare()[x][y - 1]))
					&& "0".equals(labyrinth.getSquare()[x][y + 1])) {
				return true;
			}
			break;
		case UP:
			if (("*".equals(labyrinth.getSquare()[x][y - 1]) || "-1".equals(labyrinth.getSquare()[x][y - 1]) || "i".equals(labyrinth.getSquare()[x][y - 1]))
					&& ("*".equals(labyrinth.getSquare()[x][y + 1]) || "-1".equals(labyrinth.getSquare()[x][y + 1]) || "i".equals(labyrinth.getSquare()[x][y + 1]))
					&& ("*".equals(labyrinth.getSquare()[x - 1][y]) || "-1".equals(labyrinth.getSquare()[x - 1][y]) || "i".equals(labyrinth.getSquare()[x - 1][y]))
					&& "0".equals(labyrinth.getSquare()[x + 1][y])) {
				return true;
			}
			break;
		case DOWN:
			if (("*".equals(labyrinth.getSquare()[x][y + 1]) || "-1".equals(labyrinth.getSquare()[x][y + 1]) || "i".equals(labyrinth.getSquare()[x][y + 1]))
					&& ("*".equals(labyrinth.getSquare()[x][y - 1]) || "-1".equals(labyrinth.getSquare()[x][y - 1]) || "i".equals(labyrinth.getSquare()[x][y - 1]))
					&& ("*".equals(labyrinth.getSquare()[x + 1][y]) || "-1".equals(labyrinth.getSquare()[x + 1][y]) || "i".equals(labyrinth.getSquare()[x + 1][y]))
					&& "0".equals(labyrinth.getSquare()[x - 1][y])) {
				return true;
			}
			break;
		}

		return false;
	}

	public boolean acceptWithLowestPriority(Labyrinth labyrinth, int x, int y, Orientation orientation) {
		switch (orientation) {
		case RIGHT:
			if (("*".equals(labyrinth.getSquare()[x][y + 1]) || "-1".equals(labyrinth.getSquare()[x][y + 1]) || "i".equals(labyrinth.getSquare()[x][y + 1]))
					&& ("*".equals(labyrinth.getSquare()[x + 1][y]) || "-1".equals(labyrinth.getSquare()[x + 1][y]) || "i".equals(labyrinth.getSquare()[x + 1][y]))
					&& ("*".equals(labyrinth.getSquare()[x - 1][y]) || "-1".equals(labyrinth.getSquare()[x - 1][y]) || "i".equals(labyrinth.getSquare()[x - 1][y]))
					&& ("1".equals(labyrinth.getSquare()[x][y - 1]))) {
				return true;
			}
			break;
		case LEFT:
			if (("*".equals(labyrinth.getSquare()[x + 1][y]) || "-1".equals(labyrinth.getSquare()[x + 1][y]) || "i".equals(labyrinth.getSquare()[x + 1][y]) )
					&& ("*".equals(labyrinth.getSquare()[x - 1][y]) || "-1".equals(labyrinth.getSquare()[x - 1][y]) || "i".equals(labyrinth.getSquare()[x - 1][y]))
					&& ("*".equals(labyrinth.getSquare()[x][y - 1]) || "-1".equals(labyrinth.getSquare()[x][y - 1]) || "i".equals(labyrinth.getSquare()[x][y - 1]))
					&& ("1".equals(labyrinth.getSquare()[x][y + 1]))) {
				return true;
			}
			break;
		case UP:
			if (("*".equals(labyrinth.getSquare()[x][y - 1]) || "-1".equals(labyrinth.getSquare()[x][y - 1]) || "i".equals(labyrinth.getSquare()[x][y - 1]))
					&& ("*".equals(labyrinth.getSquare()[x][y + 1]) || "-1".equals(labyrinth.getSquare()[x][y + 1]) || "i".equals(labyrinth.getSquare()[x][y + 1]))
					&& ("*".equals(labyrinth.getSquare()[x - 1][y]) || "-1".equals(labyrinth.getSquare()[x - 1][y]) || "i".equals(labyrinth.getSquare()[x - 1][y]))
					&& "1".equals(labyrinth.getSquare()[x + 1][y])) {
				return true;
			}
			break;
		case DOWN:
			if (("*".equals(labyrinth.getSquare()[x][y + 1]) || "-1".equals(labyrinth.getSquare()[x][y + 1]) || "i".equals(labyrinth.getSquare()[x][y + 1]))
					&& ("*".equals(labyrinth.getSquare()[x][y - 1]) || "-1".equals(labyrinth.getSquare()[x][y - 1]) || "i".equals(labyrinth.getSquare()[x][y - 1]))
					&& ("*".equals(labyrinth.getSquare()[x + 1][y]) || "-1".equals(labyrinth.getSquare()[x + 1][y]) || "i".equals(labyrinth.getSquare()[x + 1][y]))
					&& "1".equals(labyrinth.getSquare()[x - 1][y])) {
				return true;
			}
			break;
		
		}

		return false;
	}

}
