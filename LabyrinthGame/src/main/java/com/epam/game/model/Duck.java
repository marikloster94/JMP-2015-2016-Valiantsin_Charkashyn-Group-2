package com.epam.game.model;

import java.util.ArrayList;
import java.util.List;

import com.epam.game.behavior.Behavior;

public class Duck {

	private String name;
	private Path path;
	private List<Behavior> behaviors;
	private Orientation orientation;

	public Duck() {
		this.behaviors = new ArrayList<Behavior>();
	}

	public Duck(String name, List<Behavior> behaviors ) {
		this.name = name;
		this.behaviors = behaviors;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Path getRoute() {
		return path;
	}

	public void setRoute(Path path) {
		this.path = path;
	}

	public List<Behavior> getBehaviors() {
		return behaviors;
	}

	public void setBehaviors(List<Behavior> behaviors) {
		this.behaviors = behaviors;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public List<Behavior> getBehaviorsByParam(Labyrinth labyrinth, int x, int y, Orientation orientation){
		List<Behavior> matchingBehaviors = new ArrayList<Behavior>();
		for(Behavior behavior:behaviors){
			if(behavior.acceptWithHightestPriority(labyrinth, x, y, orientation)){
				matchingBehaviors.add(behavior);
			}
		}
		if(matchingBehaviors.isEmpty()){
			for(Behavior behavior:behaviors){
				if(behavior.acceptWithLowestPriority(labyrinth, x, y, orientation)){
					matchingBehaviors.add(behavior);
				}
			}
		}
		return matchingBehaviors;
	}
}
