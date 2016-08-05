package com.tbossi.aenigmata.puzzle.crossword.standard;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class StandardCellTest {

	@Test
	public void testNewCellEmpty() {
		StandardCell cell = new StandardCell(false);
		if (cell.getKey().isPresent() || cell.getContent().isPresent())
			fail("New empty cell is not null.");
	}
	
	@Test
	public void testCellBlack() {
		StandardCell cell = new StandardCell(false);
		assertFalse("Cell shouldn't be black.",cell.isBlack());
		cell.setBlack(true);
		assertTrue("Cell should be black.",cell.isBlack());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewCellInvalidKey() {
		StandardCell cell = new StandardCell(Optional.of(-1l), Optional.of('a'));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewCellInvalidContent() {
		StandardCell cell = new StandardCell(Optional.of(2l), Optional.of('@'));
	}

}
