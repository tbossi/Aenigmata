package com.tbossi.aenigmata.puzzle.crossword.standard;

import java.util.Optional;

import com.tbossi.aenigmata.puzzle.crossword.Word;

import javafx.beans.property.ObjectProperty;

public class StandardWord extends Word<StandardCell, StandardDirection> {

	public StandardWord(StandardDirection direction) {
		super(direction);
	}
	
	/**
	 * Gets the number of the word, which is the number of the first cell of the word.
	 * @return the number of the word.
	 */
	public long getNumber() {
		return this.get(0).getKey().orElse(0l);
	}
	/**
	 * Defines the number of the word.
	 */
	public ObjectProperty<Optional<Long>> numberProperty() {
		return this.get(0).keyProperty();
	}
	/**
	 * Sets the number of the word.
	 * @param number
	 */
	public void setNumber(long number) {
		this.get(0).setKey(Optional.of(number));
	}

}
