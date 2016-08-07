package com.tbossi.aenigmata.puzzle.crossword.cryptographic;

import java.util.Optional;

import com.tbossi.aenigmata.puzzle.crossword.BlackCell;
import com.tbossi.aenigmata.puzzle.crossword.Cell;

public class CryptographicCell extends Cell<Optional<Integer>, Optional<Character>> implements BlackCell {

private boolean isBlack;
	
	public CryptographicCell(Optional<Integer> key, Optional<Character> content) {
		setKey(key);
		setContent(content);
		setBlack(false);
	}
	
	public CryptographicCell(boolean isBlack) {
		this(null, null);
		setBlack(isBlack);
	}
	
	public CryptographicCell() {
		this(false);
	}
	
	@Override
	public void setKey(Optional<Integer> key) {
		if (key == null)
			super.setKey(Optional.empty());
		else if (!key.isPresent() || key.get() > 0)
			super.setKey(key);
		else
			throw new IllegalArgumentException("Invalid key: "+key.toString());
	}
	
	@Override
	public void setContent(Optional<Character> content) {
		if (content == null || !content.isPresent())
			super.setContent(Optional.empty());
		else if (Character.isUpperCase(content.get()) || Character.isLowerCase(content.get()))
			super.setContent(content);
		else
			throw new IllegalArgumentException("Invalid content: "+content.toString());
	}
	
	@Override
	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}

	@Override
	public boolean isBlack() {
		return isBlack;
	}
	
	public void setEmpty() {
		setKey(null);
		setContent(null);
		setBlack(false);
	}

}
