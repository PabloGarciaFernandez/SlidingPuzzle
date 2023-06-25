package main;

import java.awt.EventQueue;

import gui.PrincipalWindow;
import logic.SlidingPuzzle;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SlidingPuzzle puzzle = new SlidingPuzzle();
					PrincipalWindow frame = new PrincipalWindow(puzzle);
					frame.setVisible(true);
				} catch (Exception e) {

				}
			}
		});
	}

}
