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
		// To avoid errors in GitHub Actions because it causes an error if not.
		System.setProperty("java.awt.headless", "false");
		PrincipalWindow frame = new PrincipalWindow(puzzle);
		// If an Exception arrises test will fail
		assertTrue(true);
	}
}
