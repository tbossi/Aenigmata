package com.tbossi.aenigmata.puzzle.crossword;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class Crossword<G extends Grid> {
	private ObjectProperty<G> grid;
	
	public Crossword() {
		grid = new SimpleObjectProperty<G>();
	}
	
	public G getGrid() {
		return grid.get();
	}
	
	public void setGrid(G grid) {
		this.grid.set(grid);
	}
	
	public ObjectProperty<G> gridProperty() {
		return grid;
	}
}
