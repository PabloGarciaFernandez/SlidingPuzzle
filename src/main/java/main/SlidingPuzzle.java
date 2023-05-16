package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SlidingPuzzle {
	private static List<Integer> movements;
	private static List<Integer> initalMovements;
	private static int board[][] = new int[4][4];
	private static int initialBoard[][];

	// This method shuffles the tiles on the game board.
	public void shuffle() {
		int n = 1000;
		int randomNumber = (int) (Math.random() * 15);
		while (n > 0) {
			if (move(randomNumber)) {
				movements.add(randomNumber);
				initalMovements.add(randomNumber);
			}
			randomNumber = (int) (Math.random() * 15);
			n--;
		}
		saveInitialBoard();
	}

	private void saveInitialBoard() {
		initialBoard = new int[4][4];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				initialBoard[i][j] = board[i][j];
			}
		}
	}

	// Return the row position of number
	public int posI(int n) {
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
	public int posJ(int n) {
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
	public boolean move(int n) {

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
	private boolean canMove(int blankTileRow, int blankTileColumn,
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
	public void display() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	// This method compares the actual board with the wining board in order to
	// know if the player have won
	public boolean win() {
		return (board[0][0] == 1 && board[0][1] == 2 && board[0][2] == 3
				&& board[0][3] == 4 && board[1][0] == 5 && board[1][1] == 6
				&& board[1][2] == 7 && board[1][3] == 8 && board[2][0] == 9
				&& board[2][1] == 10 && board[2][2] == 11 && board[2][3] == 12
				&& board[3][0] == 13 && board[3][1] == 14 && board[3][2] == 15
				&& board[3][3] == 0);
	}

	// Resets the game
	public void reset() {
		System.out
				.println("--------------------------------------------------");
		System.out.println("Board reset");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = initialBoard[i][j];
			}
		}
		movements.clear();
		movements.addAll(initalMovements);
	}

	// Starts the game
	public void start() {
		createBoard();
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
				display();
			} else if (n == 18) {
				solve();
				display();
				break;
			} else {
				move(n);
				display();
				movements.add(m, n);
				m++;
			}
		} while (win() != true);
		sc.close();
		if (win() == true)
			System.out.println("CONGRATULATIONS YOU WIN!");
	}

	// This method solves the sliding puzzle.
	public void solve() {
		System.out
				.println("--------------------------------------------------");
		System.out.println("SOLUTION");
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
		initalMovements = new ArrayList<Integer>();
	}
}
