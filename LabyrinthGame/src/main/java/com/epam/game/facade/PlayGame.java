package com.epam.game.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.epam.game.algorithm.LeftHandAlgorithm;
import com.epam.game.algorithm.PassingAlgorithm;
import com.epam.game.algorithm.RightHandAlgorithm;
import com.epam.game.algorithm.SearchFirstBehaviorAlgorithm;
import com.epam.game.behavior.Behavior;
import com.epam.game.behavior.GoBackBehavior;
import com.epam.game.behavior.TurnLeftBehavior;
import com.epam.game.behavior.TurnRightBehavior;
import com.epam.game.behavior.WalkOnBehavior;
import com.epam.game.comparator.DuckComparator;
import com.epam.game.load.FileLoader;
import com.epam.game.load.Loader;
import com.epam.game.model.Duck;
import com.epam.game.model.Labyrinth;
import com.epam.game.model.Orientation;
import com.epam.game.model.Path;

public class PlayGame {

	public Labyrinth loadLabyrinth() {
		Loader loader = new FileLoader();
		List<String> inf = loader.load();
		Labyrinth labyrinth = new Labyrinth();
		String[][] square = new String[inf.size()][];
		int i = 0;
		for (String line : inf) {
			square[i] = new String[line.length()];
			for (int j = 0; j < square[i].length; j++) {
				square[i][j] = String.valueOf(line.toCharArray()[j]);
			}
			i++;
		}
		labyrinth.setSquare(square);
		HashMap<String, Integer[]> entrance = new HashMap<String, Integer[]>();
		for (i = 0; i < square.length; i++) {
			for (int j = 0; j < square[i].length; j++) {
				if (square[i][j].equals("i")) {
					entrance.put(i + "_" + j, new Integer[] { i, j });
				}
				if (square[i][j].equals("e")) {
					labyrinth.setExit(new Integer[] { i, j });
				}
			}
		}
		labyrinth.setEntrance(entrance);
		return labyrinth;
	}

	public List<Behavior> loadDuckBehaviors() {
		List<Behavior> behaviors = new ArrayList<Behavior>();
		behaviors.add(new WalkOnBehavior());
		behaviors.add(new TurnLeftBehavior());
		behaviors.add(new TurnRightBehavior());
		behaviors.add(new GoBackBehavior());
		return behaviors;
	}

	public List<Duck> loadDucks(List<Behavior> behaviors) {
		List<Duck> ducks = new ArrayList<Duck>();
		ducks.add(new Duck("Helda", behaviors));
		ducks.add(new Duck("Lara", behaviors));
		return ducks;
	}

	public List<PassingAlgorithm> loadPassingAlgorithms() {
		List<PassingAlgorithm> algs = new ArrayList<PassingAlgorithm>();
		algs.add(new SearchFirstBehaviorAlgorithm());
		algs.add(new RightHandAlgorithm());
		algs.add(new LeftHandAlgorithm());
		return algs;
	}

	public void chooseStartCoordinates(List<Duck> ducks, Labyrinth labyrinth) {
		int i = 0;
		for (Duck duck : ducks) {
			Path path = new Path();
			String routeName = (String) labyrinth.getEntrance().keySet().toArray()[i];
			path.setRouteName(routeName);
			Integer[] coordinates = labyrinth.getEntrance().get(routeName);
			// проверка на exception (null не может быть)
			duck.setOrientation(getOrientation(coordinates[0], coordinates[1], labyrinth));
			duck.setRoute(path);
			i++;
		}
	}

	private Orientation getOrientation(int x, int y, Labyrinth labyrinth) {
		if (x == 0) {
			return Orientation.DOWN;
		}
		if (y == 0) {
			return Orientation.RIGHT;
		}
		if (x == labyrinth.getSquare().length - 1) {
			return Orientation.UP;
		}
		if (y == labyrinth.getSquare()[0].length - 1) {
			return Orientation.LEFT;
		}
		return null;
	}

	public void playGame(Labyrinth labyrinth, List<Duck> ducks, List<PassingAlgorithm> algs) {
		Collections.shuffle(algs);
		int index = 0;
		for (Duck duck : ducks) {
			PassingAlgorithm alg = algs.get(index);
			System.out.println("Duck with name " + duck.getName() + " chooses passing algorithm "+alg.getClass().getSimpleName() + "  begins play on path " + duck.getRoute().getRouteName());
			Labyrinth localLabyrinth = loadLabyrinth();
			alg.pass(localLabyrinth, duck);
			System.out.println("Duck with name " + duck.getName() + " pass labyrinth after " + duck.getRoute().getRouteTime() + " steps");
			printPath(localLabyrinth);
			index++;
		}
	}

	public Duck determineWinner(List<Duck> ducks) {
		Collections.sort(ducks, new DuckComparator());
		return ducks.get(0);
	}

	private void printPath(Labyrinth labyrinth) {
		System.out.println("Path:");
		for (int i = 0; i < labyrinth.getSquare().length; i++) {
			for (int j = 0; j < labyrinth.getSquare()[i].length; j++) {
				if(labyrinth.getSquare()[i][j].length()==1){
					System.out.print(labyrinth.getSquare()[i][j] + " ");
				}else{
					System.out.print(labyrinth.getSquare()[i][j]);
				}
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
}
