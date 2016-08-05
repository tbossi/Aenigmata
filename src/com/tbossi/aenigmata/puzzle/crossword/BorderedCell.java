package com.tbossi.aenigmata.puzzle.crossword;

public interface BorderedCell {
	
	public enum CellBorders {
		LEFT,RIGHT,TOP,BOTTOM;
	}
	
	public void setBorders(CellBorders... borders);
	public CellBorders[] getBorders();
	public boolean hasBorders();
}
