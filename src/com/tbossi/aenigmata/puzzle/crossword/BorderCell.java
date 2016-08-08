package com.tbossi.aenigmata.puzzle.crossword;

public interface BorderCell {
	
	public enum CellBorders {
		LEFT,RIGHT,TOP,BOTTOM;
	}
	
	public void addBorders(CellBorders... borders);
	public void removeBorders(CellBorders... borders);
	public CellBorders[] getBorders();
	public boolean hasBorders();
	public boolean hasBorder(CellBorders border);
}
