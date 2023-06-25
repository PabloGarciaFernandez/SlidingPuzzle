package logic;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a sliding puzzle game.
 *
 * @author Pedro Zahonero Mangas
 * @author Pablo Garcia Fernandez
 * @version 1.0
 * @since 2023-05-15
 */
public class SlidingPuzzle {

	/**
	 * The list of movements that have been made.
	 */
	private List<Integer> movements;
	/**
	 * The list of movements that have made when shuffle the board.
	 */

	private List<Integer> initalMovements;
	/**
	 * The current state of the board.
	 */
	private int board[][] = new int[4][4];

	/**
	 * The initial state of the board.
	 */
	private int initialBoard[][];

	/**
	 * Constructs a new sliding puzzle game with the default initial state.
	 */
	public SlidingPuzzle() {
		createBoard();
	}

	/**
	 * Shuffles the tiles on the game board.
	 */
	public void shuffle() {
		int n = 1000;
		SecureRandom random = new SecureRandom();
		int randomNumber = random.nextInt(15);
		while (n > 0) {
			if (move(randomNumber)) {
				// movements.add(randomNumber);
				initalMovements.add(randomNumber);
			}
			randomNumber = random.nextInt(15);
			n--;
		}
		saveInitialBoard();
	}

	/**
	 * Saves the initial state of the board.
	 */
	private void saveInitialBoard() {
		initialBoard = new int[4][4];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				initialBoard[i][j] = board[i][j];
			}
		}
	}

	/**
	 * Gets the row position of the given number.
	 *
	 * @param n the number
	 * @return the row position of the number
	 */
	public int rowPosition(int n) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == n) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Gets the column position of the given number.
	 *
	 * @param n the number
	 * @return the column position of the number
	 */
	public int columnPosition(int n) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == n) {
					return j;
				}
			}
		}
		return -1;
	}

	/**
	 * Moves the n tile to the blank location if possible.
	 *
	 * @param n the number we want to move
	 * @return true if the move was successful, false otherwise
	 */
	public boolean move(int n) {

		// Get the positions(row and column) of the tiles
		int blankTileRow = rowPosition(0);
		int blankTileColumn = columnPosition(0);
		int targetTileRow = rowPosition(n);
		int targetTileColumn = columnPosition(n);

		// Checks if it is possible to move the tile
		boolean isMovePossible = canMove(blankTileRow, blankTileColumn,
				targetTileRow, targetTileColumn);

		if (isMovePossible) {
			int temp = board[targetTileRow][targetTileColumn];
			board[targetTileRow][targetTileColumn] = board[blankTileRow][blankTileColumn];
			board[blankTileRow][blankTileColumn] = temp;
			movements.add(n);
		}

		return isMovePossible;

	}

	/**
	 * Checks if the movement proposed is possible.
	 *
	 * @param blankTileRow     The row of the blank tile.
	 * @param blankTileColumn  The column of the blank tile.
	 * @param targetTileRow    The row of the target tile.
	 * @param targetTileColumn The column of the target tile.
	 * @return {@code true} if the movement is possible, {@code false}
	 *         otherwise.
	 */
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

	/**
	 * Checks if the player has won.
	 *
	 * @return {@code true} if the player has won, {@code false} otherwise.
	 */
	public boolean win() {
		return (board[0][0] == 1 && board[0][1] == 2 && board[0][2] == 3
				&& board[0][3] == 4 && board[1][0] == 5 && board[1][1] == 6
				&& board[1][2] == 7 && board[1][3] == 8 && board[2][0] == 9
				&& board[2][1] == 10 && board[2][2] == 11 && board[2][3] == 12
				&& board[3][0] == 13 && board[3][1] == 14 && board[3][2] == 15
				&& board[3][3] == 0);
	}

	/**
	 * Resets the game.
	 */
	public void reset() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = initialBoard[i][j];
			}
		}
		movements.clear();
		movements.addAll(initalMovements);
	}

	/**
	 * Solves the game.
	 */
	public void solve() {
		List<Integer> rev = new ArrayList<Integer>();
		rev.addAll(movements);
		Collections.reverse(rev);
		for (int i = 0; i < rev.size(); i++) {
			move(rev.get(i));
		}

	}

	/**
	 * Creates the sorted board.
	 */
	private void createBoard() {
		int e = 1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] += e;
				if (i == 3 && j == 3)
					board[i][j] = 0;
				e++;
			}
		}
		movements = new ArrayList<Integer>();
		initalMovements = new ArrayList<Integer>();
	}

	/**
	 * Returns the board of the puzzle.
	 *
	 * @return The board of the puzzle.
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * Returns the initial state of the board.
	 *
	 * @return The initial state of the board.
	 */
	public int[][] getInitialBoard() {
		return initialBoard;
	}

}