package com.tbossi.aenigmata.puzzle.crossword;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Grid<C extends Cell<?, ?>, D extends WordDirection> {
	
	private ObservableList<Word<C, D>> wordList;
	private ReadOnlyIntegerProperty width, height;
	private List<List<ObjectProperty<C>>> grid;
	
	public Grid(int width, int height) {
		if (width <= 0)
			throw new IllegalArgumentException("Negative column number");
		if (height <= 0)
			throw new IllegalArgumentException("Negative row number");
		
		grid = new ArrayList<List<ObjectProperty<C>>>();
		for (int i = 0; i < width; i++) {
			grid.add(new ArrayList<ObjectProperty<C>>());
			for (int j = 0; j < height; j++)
				grid.get(i).add(new SimpleObjectProperty<C>(dummyInstance()));
		}
		
		this.width = new ReadOnlyIntegerWrapper(grid.size());
		this.height = new ReadOnlyIntegerWrapper(grid.get(0).size());
		
		wordList = FXCollections.observableArrayList(new ArrayList<Word<C,D>>());
	}
	
	/**
	 * Returns an empty cell used in Grid constructor
	 * @return
	 */
	protected abstract C dummyInstance();
	
	public abstract void updateCellNumbers();
	public abstract void updateWordList();
	
	public C getCell(int x, int y) {
		return cellProperty(x, y).get();
	}
	
	public ObjectProperty<C> cellProperty(int x, int y) {
		return grid.get(x).get(y);
	}
	
	public void setCell(int x, int y, C cell) {
		cellProperty(x, y).set(cell);
	}
	
	public ObservableList<Word<C, D>> getObservableWordList() {
		return wordList;
	}
	
	public ReadOnlyIntegerProperty widthProperty() {
		return width;
	}
	
	public ReadOnlyIntegerProperty heightProperty() {
		return height;
	}
	
	public int getWidth() {
		return width.get();
	}
	
	public int getHeight() {
		return height.get();
	}
}
