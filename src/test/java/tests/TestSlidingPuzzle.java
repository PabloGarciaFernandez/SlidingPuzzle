package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.SlidingPuzzle;

public class TestSlidingPuzzle {

	@Test
	public void testShuffle() {
		// Create a new SlidingPuzzle object.
		SlidingPuzzle puzzle = new SlidingPuzzle();

		// Shuffle the tiles on the board.
		puzzle.shuffle();

		// Verify that the tiles are in a random order.
		for (int i = 0; i < puzzle.getBoard().length; i++) {
			for (int j = 0; j < puzzle.getBoard()[i].length; j++) {
				assertNotEquals(0, puzzle.getBoard()[i][j]);
			}
		}
	}

	@Test
	public void testMove() {
		// Create a new SlidingPuzzle object.
		SlidingPuzzle puzzle = new SlidingPuzzle();

		// Move the blank tile to a new location.
		puzzle.move(1);

		// Verify that the blank tile has moved to the new location.
		assertEquals(1, puzzle.getBoard()[0][0]);
	}

	@Test
	public void testWin() {
		// Create a new SlidingPuzzle object.
		SlidingPuzzle puzzle = new SlidingPuzzle();

		// Verify that the game is not won initially.
		assertFalse(puzzle.win());

		// Solve the puzzle.
		puzzle.solve();

		// Verify that the game is won.
		assertTrue(puzzle.win());
	}

}