package main;

import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SlidingPuzzle {
	private static final Random RANDOM = new Random();

	// This method shuffles the tiles on the game board.
	private static int[][] shuffle(int[][] tiles) {
		int n = 4, m = 4;
		while (n > 1) {
			int r = RANDOM.nextInt(n--);
			int t = RANDOM.nextInt(m--);
			int tmp = tiles[r][t];
			tiles[r][t] = tiles[n][m];
			tiles[n][m] = tmp;
		}
		return tiles;
	}

	// Return the row position of number
	public static int posI(int[][] tiles, int n) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				if (tiles[i][j] == n) {
					return i;
				}
			}

		}
		return n;

	}

	// Return the row position of number
	public static int posJ(int[][] tiles, int n) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				if (tiles[i][j] == n) {
					return j;
				}
			}

		}
		return n;

	}

	// This method moves the blank tile to the specified location.
	public static int[][] move(int[][] tiles, int n) {

		// Get the positions(row and column) of the tiles
		int blankTileRow = posI(tiles, 0);
		int blankTileColumn = posJ(tiles, 0);
		int targetTileRow = posI(tiles, n);
		int targetTileColumn = posJ(tiles, n);

		// Checks if it is possible to move the tile
		boolean isMovePossible = canMove(tiles, blankTileRow, blankTileColumn,
				targetTileRow, targetTileColumn);

		if (isMovePossible) {
			int temp = tiles[targetTileRow][targetTileColumn];
			tiles[targetTileRow][targetTileColumn] = tiles[blankTileRow][blankTileColumn];
			tiles[blankTileRow][blankTileColumn] = temp;
		}

		return tiles;

	}

	// This method returns if the movement proposed is possible
	private static boolean canMove(int[][] tiles, int blankTileRow,
			int blankTileColumn, int targetTileRow, int targetTileColumn) {

		if (tiles[targetTileRow][targetTileColumn] == 0) {
			return false;
		}

		// The blank tile can only move horizontally or vertically.
		int deltaRow = targetTileRow - blankTileRow;
		int deltaColumn = targetTileColumn - blankTileColumn;

		return ((Math.abs(deltaRow) <= 1 && Math.abs(deltaColumn) <= 0)
				|| (Math.abs(deltaRow) <= 0 && Math.abs(deltaColumn) <= 1));

	}

	// This method displays the game
	public static int[][] display(int[][] tiles) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {

				System.out.print(tiles[i][j] + " ");
			}
			System.out.println();
		}
		return tiles;

	}

	// This method compares the actual board with the wining board in order to
	// know if the player have won
	public static boolean win(int[][] tiles) {
		if (tiles[0][0] == 1 && tiles[0][1] == 2 && tiles[0][2] == 3
				&& tiles[0][3] == 4 && tiles[1][0] == 5 && tiles[1][1] == 6
				&& tiles[1][2] == 7 && tiles[1][3] == 8 && tiles[2][0] == 9
				&& tiles[2][1] == 10 && tiles[2][2] == 11 && tiles[2][3] == 12
				&& tiles[3][0] == 13 && tiles[3][1] == 14 && tiles[3][2] == 15
				&& tiles[3][3] == 0) {
			return true;
		}
		return false;
	}

	// Resets the game
	private void reset() {
		SlidingPuzzle p = new SlidingPuzzle();
	}

	// Starts the game
	private void start(int[][] tiles, int movements[]) {
		display(tiles);
		int m = 0;
		for (int i = 0; i < movements.length; i++) {
			if (movements[i] == 0) {
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
				display(solve(tiles, movements));
				break;

			}
			display(move(tiles, n));
			movements[m] = n;
			m++;
		} while (win(tiles) != true);
		if (win(tiles) == true)
			System.out.println("CONGRATULATIONS YOU WIN!");
	}

	// This method reverses the order of an array.
	private static int[] inverseArray(int movements[]) {
		int aux = 0;
		for (int i = 0; i < movements.length / 2; i++) {
			aux = movements[i];
			movements[i] = movements[movements.length - 1 - i];
			movements[movements.length - 1 - i] = aux;
		}
		return movements;
	}

	// This method solves the sliding puzzle.
	private static int[][] solve(int tiles[][], int movements[]) {
		int[] inv = inverseArray(movements);
		for (int i = 0; i < inv.length; i++) {
			move(tiles, inv[i]);
		}
		return tiles;
	}

	SlidingPuzzle() {
		int tiles[][] = new int[4][4];
		int movements[] = new int[1000];
		int e = 1;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				tiles[i][j] += e;
				if (i == 3 && j == 3)
					tiles[i][j] = tiles[i][j] * 0;
				e++;

			}

		}
		int n = 0;
		int m = 0;
		System.out.print(
				"SLIDE PUZZLE\nIf you want to start write 16, if you want to reset the board write 17 and if you want to solve the board write 18.\n\n");
		display(shuffle(tiles));
		Scanner sc = new Scanner(System.in);
		do {
			String num_string = JOptionPane
					.showInputDialog("Give me a number: ");
			n = Integer.parseInt(num_string);
			if (n == 16) {
				System.out.println("We start the game. GOOD LUCK!");
				start(tiles, movements);
				break;
			}
			display(move(tiles, n));
			movements[m] = n;
			m++;
		} while (n != 16);

		sc.close();

	}

	public static void main(String[] args) {
		SlidingPuzzle p = new SlidingPuzzle();

	}

}
