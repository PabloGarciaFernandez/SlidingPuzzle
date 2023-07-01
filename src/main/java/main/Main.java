package main;

import java.awt.EventQueue;

import gui.PrincipalWindow;
import logic.SlidingPuzzle;
import logic.util.Logger;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Logger.getInstance().log(Logger.INFO,
				"Execution of main() at Main.java");
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SlidingPuzzle puzzle = new SlidingPuzzle();
					PrincipalWindow frame = new PrincipalWindow(puzzle);
					frame.setVisible(true);
				} catch (Exception e) {
					Logger.getInstance().log(Logger.ERROR, e.getMessage());
				}
			}
		});
	}

}
