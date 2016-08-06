package com.tbossi.aenigmata.puzzle.crossword;

import java.util.ArrayList;

import javafx.beans.property.ObjectProperty;

public abstract class Word<E extends Cell<?, ?>, D extends WordDirection> extends ArrayList<E> {
	
	private ObjectProperty<D> direction;
	
	public Word(D direction) {
		super();
		setDirection(direction);
	}
	
	public void setDirection(D direction) {
		this.direction.set(direction);
	}
	
	public D getDirection() {
		return direction.get();
	}
	
	public ObjectProperty<D> directionProperty() {
		return direction;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		this.forEach(c -> sb.append(c.getContent()));
		return sb.toString();
	}
}
