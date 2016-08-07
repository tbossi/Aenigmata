package com.tbossi.aenigmata.puzzle.crossword.cryptographic;

import com.tbossi.aenigmata.puzzle.crossword.Crossword;

public class CryptographicCrossword extends Crossword<CryptographicGrid> {
	
	public CryptographicCrossword(int cols, int rows) {
    	super();
		super.setGrid(new CryptographicGrid(cols, rows));
	}
}
