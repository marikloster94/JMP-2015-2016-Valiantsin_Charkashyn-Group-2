package com.epam.game.comparator;

import java.util.Comparator;

import com.epam.game.model.Duck;

public class DuckComparator implements Comparator<Duck> {

	@Override
	public int compare(Duck duck1, Duck duck2) {
		return duck1.getRoute().getRouteTime() - duck2.getRoute().getRouteTime();
	}

}
