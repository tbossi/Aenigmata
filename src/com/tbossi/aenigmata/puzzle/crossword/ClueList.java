package com.tbossi.aenigmata.puzzle.crossword;

import javafx.collections.ObservableList;

public interface ClueList<D extends WordDirection, C extends Clue<?>> {
	
	public ObservableList<C> getObservableList(D direction);
	public void addClue(C clue, D direction);
}
