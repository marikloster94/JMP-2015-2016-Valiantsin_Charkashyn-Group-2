package com.epam.game.model;

import java.util.ArrayList;
import java.util.List;

import com.epam.game.behavior.Behavior;

public class Duck {

	private String name;
	private Path route;
	private List<Behavior> behaviors;

	public Duck() {
		this.behaviors = new ArrayList<Behavior>();
	}

	public Duck(String name) {
		this.name = name;
		this.behaviors = new ArrayList<Behavior>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Path getRoute() {
		return route;
	}

	public void setRoute(Path route) {
		this.route = route;
	}

	public List<Behavior> getBehaviors() {
		return behaviors;
	}

	public void setBehaviors(List<Behavior> behaviors) {
		this.behaviors = behaviors;
	}

}
