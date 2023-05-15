package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SlidingPuzzle {
	private static final Random RANDOM = new Random();
	private static List<Integer> movements;
	private static int board[][] = new int[4][4];

	// This method shuffles the tiles on the game board.
	private static void shuffle() {
		int n = 1000;
		int randomNumber = (int) (Math.random() * 15);
		while (n > 0) {
			if (move(randomNumber))
				movements.add(randomNumber);
			randomNumber = (int) (Math.random() * 15);
			n--;
		}
	}

	// Return the row position of number
	public static int posI(int n) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == n) {
					return i;
				}
			}

		}
		return n;

	}

	// Return the row position of number
	public static int posJ(int n) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == n) {
					return j;
				}
			}
		}
		return n;
	}

	// This method moves the blank tile to the specified location.
	public static boolean move(int n) {

		// Get the positions(row and column) of the tiles
		int blankTileRow = posI(0);
		int blankTileColumn = posJ(0);
		int targetTileRow = posI(n);
		int targetTileColumn = posJ(n);

		// Checks if it is possible to move the tile
		boolean isMovePossible = canMove(blankTileRow, blankTileColumn,
				targetTileRow, targetTileColumn);

		if (isMovePossible) {
			int temp = board[targetTileRow][targetTileColumn];
			board[targetTileRow][targetTileColumn] = board[blankTileRow][blankTileColumn];
			board[blankTileRow][blankTileColumn] = temp;
		}

		return isMovePossible;

	}

	// This method returns if the movement proposed is possible
	private static boolean canMove(int blankTileRow, int blankTileColumn,
			int targetTileRow, int targetTileColumn) {

		if (board[targetTileRow][targetTileColumn] == 0) {
			return false;
		}

		// The blank tile can only move horizontally or vertically.
		int deltaRow = targetTileRow - blankTileRow;
		int deltaColumn = targetTileColumn - blankTileColumn;

		return ((Math.abs(deltaRow) <= 1 && Math.abs(deltaColumn) <= 0)
				|| (Math.abs(deltaRow) <= 0 && Math.abs(deltaColumn) <= 1));

	}

	// This method displays the game
	public static void display() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	// This method compares the actual board with the wining board in order to
	// know if the player have won
	public static boolean win() {
		if (board[0][0] == 1 && board[0][1] == 2 && board[0][2] == 3
				&& board[0][3] == 4 && board[1][0] == 5 && board[1][1] == 6
				&& board[1][2] == 7 && board[1][3] == 8 && board[2][0] == 9
				&& board[2][1] == 10 && board[2][2] == 11 && board[2][3] == 12
				&& board[3][0] == 13 && board[3][1] == 14 && board[3][2] == 15
				&& board[3][3] == 0) {
			return true;
		}
		return false;
	}

	// Resets the game
	private void reset() {
		SlidingPuzzle p = new SlidingPuzzle();
	}

	// Starts the game
	private void start() {
		shuffle();
		display();
		int m = 0;
		for (int i = 0; i < movements.size(); i++) {
			if (movements.get(i) == 0) {
				m = i + 1;
				break;
			}
		}
		Scanner sc = new Scanner(System.in);
		do {
			String num_string = JOptionPane
					.showInputDialog("Give me an integer: ");
			int n = Integer.parseInt(num_string);
			System.out.print("\n");
			if (n == 17) {
				reset();
			}
			if (n == 18) {
				solve();
				display();
				break;
			}
			move(n);
			display();
			movements.add(m, n);
			m++;
		} while (win() != true);
		if (win() == true)
			System.out.println("CONGRATULATIONS YOU WIN!");
	}

	// This method solves the sliding puzzle.
	private static void solve() {
		System.out
				.println("--------------------------------------------------");
		Collections.reverse(movements);
		for (int i = 0; i < movements.size(); i++) {
			move(movements.get(i));
		}

	}

	private void createBoard() {
		int e = 1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] += e;
				if (i == 3 && j == 3)
					board[i][j] = board[i][j] * 0;
				e++;
			}
		}
		movements = new ArrayList<Integer>();
	}

	SlidingPuzzle() {
		int n = 0;
		int m = 0;
		System.out.print(
				"SLIDE PUZZLE\nIf you want to start write 16, if you want to reset the board write 17 and if you want to solve the board write 18.\n\n");
		Scanner sc = new Scanner(System.in);
		do {
			String num_string = JOptionPane
					.showInputDialog("Give me a number: ");
			n = Integer.parseInt(num_string);
			if (n == 16) {
				System.out.println("We start the game. GOOD LUCK!");
				createBoard();
				start();
				break;
			}
			move(n);
			display();
			movements.add(m, n);
			m++;
		} while (n != 16);

		sc.close();

	}

	public static void main(String[] args) {
		SlidingPuzzle p = new SlidingPuzzle();

	}

}
