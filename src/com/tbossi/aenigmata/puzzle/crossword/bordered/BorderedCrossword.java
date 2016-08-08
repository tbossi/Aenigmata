package com.tbossi.aenigmata.puzzle.crossword.bordered;

import com.tbossi.aenigmata.puzzle.crossword.ClueList;
import com.tbossi.aenigmata.puzzle.crossword.Crossword;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BorderedCrossword extends Crossword<BorderedGrid> implements ClueList<BorderedDirection, BorderedClue>{
	
	private ObservableList<BorderedClue> across;
	private ObservableList<BorderedClue> down;
	
	public BorderedCrossword(int width, int height) {
		super();
		super.setGrid(new BorderedGrid(width, height));
		across = FXCollections.observableArrayList();
    	down = FXCollections.observableArrayList();
	}
	
	@Override
	public ObservableList<BorderedClue> getObservableList(BorderedDirection direction) {
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
	public void addClue(BorderedClue clue, BorderedDirection direction) {
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
