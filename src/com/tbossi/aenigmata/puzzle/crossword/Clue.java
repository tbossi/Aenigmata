package com.tbossi.aenigmata.puzzle.crossword;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class Clue<W extends Word<?,?>> {
	private ObjectProperty<W> word;
	private SimpleStringProperty clue;
	
	public Clue(String clue, W word) {
		this.clue = new SimpleStringProperty();
    	setClue(clue);
    	this.word = new SimpleObjectProperty<W>();
    	setWord(word);
	}
	
	public ObjectProperty<W> wordProperty() {
		return word;
	}
	
	public W getWord() {
		return word.get();
	}
	
	public void setWord(W word) {
		if (word == null)
			throw new IllegalArgumentException("Word should not be null.");
		this.word.set(word);
	}
	
	/**
     * Defines the clue of the word.
     */
    public SimpleStringProperty clueProperty() {
    	return this.clue;
    } 
    /**
     * Gets the clue string of the word.
     * @return the clue string.
     */
    public String getClue() {
    	return this.clue.get();
    }
    /**
     * Sets the clue string.
     * @param clue
     */
    public void setClue(String clue) {
    	this.clue.set(clue);
    }
}
