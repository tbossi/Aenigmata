package com.tbossi.aenigmata.puzzle.crossword.bordered;

import com.tbossi.aenigmata.puzzle.crossword.BorderCell.CellBorders;

import java.util.Optional;

import com.tbossi.aenigmata.puzzle.crossword.Grid;

public class BorderedGrid extends Grid<BorderedCell, BorderedDirection> {

	public BorderedGrid(int width, int height) {
		super(width, height);
	}

	@Override
	protected BorderedCell dummyInstance() {
		return new BorderedCell();
	}

	@Override
	public void updateCellNumbers() {
		long counter = 1;
		boolean up, down, left, right;
		for (int y = 0; y < getHeight(); y++)
			for (int x = 0; x < getWidth(); x++) {
				up    = (y==0) ? true : (
							getCell(x, y-1).hasBorder(CellBorders.BOTTOM)
							|| getCell(x, y).hasBorder(CellBorders.TOP));
				down  = (y==getHeight()-1) ? true : (
							getCell(x, y+1).hasBorder(CellBorders.TOP)
							|| getCell(x, y).hasBorder(CellBorders.BOTTOM));
				left  = (x==0) ? true : (
							getCell(x-1, y).hasBorder(CellBorders.RIGHT)
							|| getCell(x, y).hasBorder(CellBorders.LEFT));
				right = (x==getWidth()-1) ? true : (
							getCell(x+1, y).hasBorder(CellBorders.LEFT)
							|| getCell(x, y).hasBorder(CellBorders.RIGHT));
				
				// se non valgono le condizioni in cui la casella non è numerata,
				// allora la casella è numerata
				
				if (!( (!up && left && !down && right) ||
					   (up && !left && down && !right) ||
					   (!up && right) ||
					   (down && !left) ||
					   (up && down && left && right) ||
					   (!up && !down && !left && !right))) {
					getCell(x, y).setKey(Optional.of(counter));
					counter++;
				} else
					getCell(x, y).setKey(Optional.empty());
			}
	}

	@Override
	public void updateWordList() {
		boolean up, down, left, right;
		getObservableWordList().clear();
		
		for (int y = 0; y < getHeight(); y++)
			for (int x = 0; x < getWidth(); x++) {
				up    = (y==0) ? true : (
							getCell(x, y-1).hasBorder(CellBorders.BOTTOM)
							|| getCell(x, y).hasBorder(CellBorders.TOP));
				down  = (y==getHeight()-1) ? true : (
							getCell(x, y+1).hasBorder(CellBorders.TOP)
							|| getCell(x, y).hasBorder(CellBorders.BOTTOM));
				left  = (x==0) ? true : (
							getCell(x-1, y).hasBorder(CellBorders.RIGHT)
							|| getCell(x, y).hasBorder(CellBorders.LEFT));
				right = (x==getWidth()-1) ? true : (
							getCell(x+1, y).hasBorder(CellBorders.LEFT)
							|| getCell(x, y).hasBorder(CellBorders.RIGHT));
				
				if ((!left || !right) && !isCellContainedInWordList(getCell(x, y), BorderedDirection.ACROSS)) {
					BorderedWord word = new BorderedWord(BorderedDirection.ACROSS);
					int wordX = x;
					while (wordX > 0 && !(getCell(wordX-1, y).hasBorder(CellBorders.RIGHT) || getCell(wordX, y).hasBorder(CellBorders.LEFT)))
						wordX--;
					while (wordX < getWidth() && !(getCell(wordX-1, y).hasBorder(CellBorders.RIGHT) || getCell(wordX, y).hasBorder(CellBorders.LEFT))) {
						word.add(getCell(wordX, y));
						wordX++;
					}
					getObservableWordList().add(word);
				}
				
				if ((!up || !down) && !isCellContainedInWordList(getCell(x, y), BorderedDirection.DOWN)) {
					BorderedWord word = new BorderedWord(BorderedDirection.DOWN);
					int wordY = y;
					while (wordY > 0 && !(getCell(x, wordY-1).hasBorder(CellBorders.BOTTOM) || getCell(x, wordY).hasBorder(CellBorders.TOP)))
						wordY--;
					while (wordY < getHeight() && !(getCell(x, wordY-1).hasBorder(CellBorders.BOTTOM) || getCell(x, wordY).hasBorder(CellBorders.TOP))) {
						word.add(getCell(x, wordY));
						wordY++;
					}
					getObservableWordList().add(word);
				}
			}
	}

}
