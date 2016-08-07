package com.tbossi.aenigmata.puzzle.crossword.cryptographic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.tbossi.aenigmata.puzzle.crossword.Grid;

public class CryptographicGrid extends Grid<CryptographicCell, CryptographicDirection> {
	private Map<Character, Integer> association;
	
	public CryptographicGrid(int width, int height) {
		super(width, height);
		association = new HashMap<Character, Integer>();
	}

	@Override
	protected CryptographicCell dummyInstance() {
		return new CryptographicCell();
	}

	@Override
	public void updateCellNumbers() {
		int counter = 1;
		association = new HashMap<Character, Integer>();
		for (int y = 0; y < getHeight(); y++)
			for (int x = 0; x < getWidth(); x++) {
				if (!getCell(x, y).isBlack() && getCell(x, y).getContent().isPresent()) {
					char cont = getCell(x, y).getContent().get();
					if (association.containsKey(cont))
						getCell(x, y).setKey(Optional.of(association.get(cont)));
					else {
						association.put(cont, counter);
						getCell(x, y).setKey(Optional.of(association.get(cont)));
						counter++;
					}
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
					
					if ((!left || !right) && !isCellContainedInWordList(getCell(x, y), CryptographicDirection.ACROSS)) {
						CryptographicWord word = new CryptographicWord(CryptographicDirection.ACROSS);
						int wordX = x;
						while (wordX > 0 && !getCell(wordX-1, y).isBlack())
							wordX--;
						while (wordX < getWidth() && !getCell(wordX, y).isBlack()) {
							word.add(getCell(wordX, y));
							wordX++;
						}
						getObservableWordList().add(word);
					}
					
					if ((!up || !down) && !isCellContainedInWordList(getCell(x, y), CryptographicDirection.DOWN)) {
						CryptographicWord word = new CryptographicWord(CryptographicDirection.DOWN);
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
