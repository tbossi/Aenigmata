package com.tbossi.aenigmata.puzzle.crossword.standard;

import java.util.Optional;

import com.tbossi.aenigmata.puzzle.crossword.Grid;

public class StandardGrid extends Grid<StandardCell, StandardDirection> {

	public StandardGrid(int width, int height) {
		super(width, height);
	}

	@Override
	protected StandardCell dummyInstance() {
		return new StandardCell();
	}

	@Override
	public void updateCellNumbers() {
		long counter = 1;
		boolean up, down, left, right;
		for (int y = 0; y < getHeight(); y++)
			for (int x = 0; x < getWidth(); x++) {
				if (!getCell(x, y).isBlack()) {
					up    = (y==0) ? true : getCell(x, y-1).isBlack();
					down  = (y==getHeight()-1) ? true : getCell(x, y+1).isBlack();
					left  = (x==0) ? true : getCell(x-1, y).isBlack();
					right = (x==getWidth()-1) ? true : getCell(x+1, y).isBlack();
					
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
	}

	@Override
	public void updateWordList() {
		boolean up, down, left, right;
		getObservableWordList().clear();
		
		for (int y = 0; y < getHeight(); y++)
			for (int x = 0; x < getWidth(); x++) {
				if (!getCell(x, y).isBlack()) {
					up    = (y==0) ? true : getCell(x, y-1).isBlack();
					down  = (y==getHeight()-1) ? true : getCell(x, y+1).isBlack();
					left  = (x==0) ? true : getCell(x-1, y).isBlack();
					right = (x==getWidth()-1) ? true : getCell(x+1, y).isBlack();
					
					if ((!left || !right) && !isCellContainedInWordList(getCell(x, y), StandardDirection.ACROSS)) {
						StandardWord word = new StandardWord(StandardDirection.ACROSS);
						int wordX = x;
						while (wordX > 0 && !getCell(wordX-1, y).isBlack())
							wordX--;
						while (wordX < getWidth() && !getCell(wordX, y).isBlack()) {
							word.add(getCell(wordX, y));
							wordX++;
						}
						getObservableWordList().add(word);
					}
					
					if ((!up || !down) && !isCellContainedInWordList(getCell(x, y), StandardDirection.DOWN)) {
						StandardWord word = new StandardWord(StandardDirection.DOWN);
						int wordY = y;
						while (wordY > 0 && !getCell(x, wordY-1).isBlack())
							wordY--;
						while (wordY < getHeight() && !getCell(x, wordY).isBlack()) {
							word.add(getCell(x, wordY));
							wordY++;
						}
						getObservableWordList().add(word);
					}
				}
			}
	}

}
