package tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.SlidingPuzzle;

public class TestSlidingPuzzle {

	@Test
	void testShuffle() {
		SlidingPuzzle puzzle = new SlidingPuzzle();
		int board[][] = puzzle.getBoard();
		puzzle.shuffle();

		// Check that the board is in a shuffled state.
		assertNotEquals(board, puzzle.getInitialBoard());
	}

	@Test
	void testMove() {
		SlidingPuzzle puzzle = new SlidingPuzzle();
		puzzle.move(15);

		// Check that the blank tile has moved to the left.
		assertEquals(15, puzzle.getBoard()[3][3]);
		assertEquals(0, puzzle.getBoard()[3][2]);
	}

	@Test
	void testWin() {
		SlidingPuzzle puzzle = new SlidingPuzzle();
		puzzle.shuffle();
		puzzle.solve();
		assertTrue(puzzle.win());
	}

	@Test
	void testReset() {
		SlidingPuzzle puzzle = new SlidingPuzzle();
		puzzle.shuffle();
		for (int i = 1; i <= 15; i++) {
			puzzle.move(i);
		}
		puzzle.reset();

		// Check that the board has been reset to the initial state.
		assertArrayEquals(puzzle.getBoard(), puzzle.getInitialBoard());
	}

	@Test
	void testSolve() {
		SlidingPuzzle puzzle = new SlidingPuzzle();
		puzzle.shuffle();
		puzzle.solve();

		// Check that the game has been solved.
		assertTrue(puzzle.win());
	}

}