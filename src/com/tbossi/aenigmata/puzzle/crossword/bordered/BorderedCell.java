package com.tbossi.aenigmata.puzzle.crossword.bordered;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.tbossi.aenigmata.puzzle.crossword.BorderCell;
import com.tbossi.aenigmata.puzzle.crossword.Cell;

public class BorderedCell extends Cell<Optional<Long>, Optional<Character>> implements BorderCell {
	private EnumSet<CellBorders> borders;
	
	public BorderedCell(Optional<Long> key, Optional<Character> content) {
		setKey(key);
		setContent(content);
		borders = EnumSet.noneOf(CellBorders.class);
	}
	
	public BorderedCell() {
		this(null,null);
	}
	
	@Override
	public void setKey(Optional<Long> key) {
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
	public void addBorders(CellBorders... borders) {
		EnumSet<CellBorders> resulting = EnumSet.copyOf(this.borders);
		for (CellBorders b: borders)
			resulting.add(b);
		
		if (resulting.size() < CellBorders.values().length)
			this.borders = resulting;
		else
			throw new IllegalArgumentException("At least one side must not have a border.");
	}
	
	public void setEmpty() {
		setKey(null);
		setContent(null);
		removeBorders(CellBorders.values());
	}
	
	@Override
	public void removeBorders(CellBorders... borders) {
		for (CellBorders b: borders)
			this.borders.remove(b);
	}

	@Override
	public CellBorders[] getBorders() {
		return borders.toArray(new CellBorders[borders.size()]);
	}

	@Override
	public boolean hasBorders() {
		return !borders.isEmpty();
	}

	@Override
	public boolean hasBorder(CellBorders border) {
		return borders.contains(border);
	}

}
