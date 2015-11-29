package com.epam.game.algorithm;

import java.util.List;

import com.epam.game.behavior.Behavior;
import com.epam.game.behavior.TurnRightBehavior;
import com.epam.game.model.Duck;
import com.epam.game.model.Labyrinth;

public class RightHandAlgorithm implements PassingAlgorithm {

	public void pass(Labyrinth labyrinth, Duck duck) {
		Integer []coordinates = labyrinth.getEntrance().get(duck.getRoute().getRouteName());
		int x = coordinates[0];
		int y = coordinates[1];
		int pathLenght = 0;
		while(x!=labyrinth.getExit()[0] || y!=labyrinth.getExit()[1]){
			List<Behavior> behaiviors = duck.getBehaviorsByParam(labyrinth, x, y, duck.getOrientation());
			if(!behaiviors.isEmpty()){
				Behavior duckBehavior = null;
				int index = indexOfTurnRightBehavior(behaiviors);
				duckBehavior = behaiviors.get(index == -1 ? 0: index);
				coordinates = duckBehavior.move(labyrinth, coordinates, duck);
				x = coordinates[0];
				y = coordinates[1];
				pathLenght++;
			}
		}
		duck.getRoute().setRouteTime(pathLenght);
		

	}
	
	private int indexOfTurnRightBehavior(List<Behavior> behaiviors){
		int index = 0;
		for(Behavior behavior:behaiviors){
			if(behavior instanceof TurnRightBehavior){
				return index;
			}
			index++;
		}
		return -1;
	}

}
