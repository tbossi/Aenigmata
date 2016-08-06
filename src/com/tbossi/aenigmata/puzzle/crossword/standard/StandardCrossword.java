package com.tbossi.aenigmata.puzzle.crossword.standard;

import com.tbossi.aenigmata.puzzle.crossword.ClueList;
import com.tbossi.aenigmata.puzzle.crossword.Crossword;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StandardCrossword extends Crossword<StandardGrid> implements ClueList<StandardDirection, StandardClue> {
	
	private ObservableList<StandardClue> across;
	private ObservableList<StandardClue> down;
	
	public StandardCrossword(int width, int height) {
		super();
		super.setGrid(new StandardGrid(width, height));
		across = FXCollections.observableArrayList();
    	down = FXCollections.observableArrayList();
	}
	
	@Override
	public ObservableList<StandardClue> getObservableList(StandardDirection direction) {
		switch(direction) {
		case ACROSS:
			return across;
		case DOWN:
			return down;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void addClue(StandardClue clue, StandardDirection direction) {
		switch(direction) {
		case ACROSS:
			across.add(clue);
			break;
		case DOWN:
			down.add(clue);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
	
}
