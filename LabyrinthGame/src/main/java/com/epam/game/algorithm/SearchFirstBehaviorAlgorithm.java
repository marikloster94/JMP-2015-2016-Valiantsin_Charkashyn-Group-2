package com.epam.game.algorithm;

import java.util.List;

import com.epam.game.behavior.Behavior;
import com.epam.game.model.Duck;
import com.epam.game.model.Labyrinth;

public class SearchFirstBehaviorAlgorithm implements PassingAlgorithm {

	public void pass(Labyrinth labyrinth,  Duck duck) {
		Integer []coordinates = labyrinth.getEntrance().get(duck.getRoute().getRouteName());
		int x = coordinates[0];
		int y = coordinates[1];
		int pathLenght = 0;
		while(x!=labyrinth.getExit()[0] || y!=labyrinth.getExit()[1]){
			List<Behavior> behaiviors = duck.getBehaviorsByParam(labyrinth, x, y, duck.getOrientation());
			if(!behaiviors.isEmpty()){
				Behavior duckBehavior = null;
				duckBehavior = behaiviors.get(0);
				coordinates = duckBehavior.move(labyrinth, coordinates, duck);
				x = coordinates[0];
				y = coordinates[1];
				pathLenght++;
			}
		}
		duck.getRoute().setRouteTime(pathLenght);

	}

}
