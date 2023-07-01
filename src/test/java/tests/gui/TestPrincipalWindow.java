package tests.gui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.AbstractButton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gui.PrincipalWindow;
import logic.SlidingPuzzle;

public class TestPrincipalWindow {
	@Mock
	private PrincipalWindow window;
	private SlidingPuzzle puzzle;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		puzzle = new SlidingPuzzle();
		window = new PrincipalWindow(puzzle);
	}

	@Test
	void testWindowCreation() {
		assertNotNull(window);
	}

	@Test
	void testResetButton() {
		window.getBtReset().doClick();
		// Verify that the puzzle is reset
		assertFalse(puzzle.win());
	}

	@Test
	void testSolveButton() {
		window.getBtSolve().doClick();
		// Verify that the puzzle is solved
		assertTrue(puzzle.win());
	}

	@Test
	void testTileClick() {
		// Simulate a tile click
		((AbstractButton) window.getPnBoard().getComponent(0)).doClick();
		assertFalse(puzzle.win());
	}
}
