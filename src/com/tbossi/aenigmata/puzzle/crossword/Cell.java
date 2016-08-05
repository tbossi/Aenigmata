package com.tbossi.aenigmata.puzzle.crossword;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class Cell<K, C> {
	
	private ObjectProperty<K> key;
	private ObjectProperty<C> content;
	private SimpleBooleanProperty cellVisible;
	
	public Cell() {
		key = new SimpleObjectProperty<K>();
		content = new SimpleObjectProperty<C>();
		cellVisible = new SimpleBooleanProperty();
	}
	
	public ObjectProperty<K> keyProperty() {
		return key;
	}
	
	public K getKey() {
		return key.get();
	}
	
	public void setKey(K key) {
		this.key.set(key);
	}
	
	
	public ObjectProperty<C> contentProperty() {
		return content;
	}
	
	public C getContent() {
		return content.get();
	}
	
	public void setContent(C content) {
		this.content.set(content);
	}
	
	public SimpleBooleanProperty cellVisibleProperty() {
		return cellVisible;
	}
	/**
	 * @return the cellVisible
	 */
	public boolean getCellVisible() {
		return cellVisible.get();
	}
	/**
	 * @param cellVisible the cellVisible to set
	 */
	public void setCellVisible(boolean cellVisible) {
		this.cellVisible.set(cellVisible);
	}
}
