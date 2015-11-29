package com.epam.game.main;

import java.util.List;

import com.epam.game.behavior.Behavior;
import com.epam.game.facade.PlayGame;
import com.epam.game.model.Duck;
import com.epam.game.model.Labyrinth;

public class Runner {

	public static void main(String[] args) {
		PlayGame player = new PlayGame();
		System.out.println("Loading labyrinth");
		Labyrinth labyrinth = player.loadLabyrinth();
		System.out.println("Labyrinth loaded");
		System.out.println("Loading behaviors");
		List<Behavior> behaviors = player.loadDuckBehaviors();
		System.out.println("Behaviors loaded");
		System.out.println("Loading ducks");
		List<Duck> ducks = player.loadDucks(behaviors);
		System.out.println("Ducks loaded");
		System.out.println("Choose enterance for duck");
		player.chooseStartCoordinates(ducks, labyrinth);
		System.out.println("Enterance choosed");
		player.playGame(labyrinth, ducks, player.loadPassingAlgorithms());
		Duck winner = player.determineWinner(ducks);
		System.out.println("The winner is "+ winner.getName() +" .Winner exit from labyrinth after "+winner.getRoute().getRouteTime() +" steps");
	}
}
