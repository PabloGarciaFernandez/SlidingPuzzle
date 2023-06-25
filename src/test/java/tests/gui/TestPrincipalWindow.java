package tests.gui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gui.PrincipalWindow;
import logic.SlidingPuzzle;

public class TestPrincipalWindow {

	private SlidingPuzzle puzzle;

	@BeforeEach
	void init() {
		puzzle = new SlidingPuzzle();
	}

	@Test
	void testInitialization() {
		PrincipalWindow frame = new PrincipalWindow(puzzle);
		frame.setVisible(false);
		// If an Exception arrises test will fail
		assertTrue(true);
	}
}
