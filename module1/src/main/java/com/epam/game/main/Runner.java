package com.epam.game.main;

import java.io.FileNotFoundException;
import java.util.List;

import com.epam.game.behavior.Behavior;
import com.epam.game.facade.PlayGame;
import com.epam.game.model.Duck;
import com.epam.game.model.Labyrinth;

public class Runner {

	public static void main(String[] args) {
		try {
			PlayGame player = new PlayGame();
			Labyrinth labyrinth = player.loadLabyrinth();
			System.out.println("Labyrinth loaded");
			player.chekLabyrinth(labyrinth);
			System.out.println("Labyrinth checked");;
			List<Behavior> behaviors = player.loadDuckBehaviors();
			System.out.println("Behaviors loaded");
			List<Duck> ducks = player.loadDucks(behaviors);
			System.out.println("Ducks loaded");
			player.chooseStartCoordinates(ducks, labyrinth);
			System.out.println("Enterance for duck choosed");
			for(Duck duck:ducks){
				labyrinth = player.loadLabyrinth();
				player.playGame(labyrinth, duck, player.loadPassingAlgorithms());
				player.showRoute(labyrinth);
			}
			Duck winner = player.determineWinner(ducks);
			System.out.println("The winner is " + winner.getName() + ".Winner exit from labyrinth after " + winner.getRoute().getRouteTime() + " steps");
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
