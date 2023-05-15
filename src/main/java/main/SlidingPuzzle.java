package main;

import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SlidingPuzzle {
	private static final Random RANDOM = new Random();
	private int tiles[];

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

	public static int[][] move(int[][] tiles, int n) {

		int i1 = posI(tiles, 0);
		int j1 = posJ(tiles, 0);
		int i2 = posI(tiles, n);
		int j2 = posJ(tiles, n);
		int aux = 0;
		// for(int i=0;i<tiles.length;i++){
		// for(int j=0;j<tiles.length;j++) {
		if (tiles[1][0] == 0 || tiles[2][0] == 0) {
			if (tiles[i1 + 1][j1] == tiles[i2][j2]
					|| tiles[i1 - 1][j1] == tiles[i2][j2]
					|| tiles[i1][j1 + 1] == tiles[i2][j2]) {
				aux = tiles[i2][j2];
				tiles[i2][j2] = tiles[i1][j1];
				tiles[i1][j1] = aux;
			}
		} else if (tiles[3][0] == 0) {
			if (tiles[i1 - 1][j1] == tiles[i2][j2]
					|| tiles[i1][j1 + 1] == tiles[i2][j2]) {
				aux = tiles[i2][j2];
				tiles[i2][j2] = tiles[i1][j1];
				tiles[i1][j1] = aux;
			}
		} else if (tiles[1][3] == 0 || tiles[2][3] == 0) {
			if (tiles[i1 + 1][j1] == tiles[i2][j2]
					|| tiles[i1 - 1][j1] == tiles[i2][j2]
					|| tiles[i1][j1 - 1] == tiles[i2][j2]) {
				aux = tiles[i2][j2];
				tiles[i2][j2] = tiles[i1][j1];
				tiles[i1][j1] = aux;
			}
		} else if (tiles[0][3] == 0) {
			if (tiles[i1 + 1][j1] == tiles[i2][j2]
					|| tiles[i1][j1 - 1] == tiles[i2][j2]) {
				aux = tiles[i2][j2];
				tiles[i2][j2] = tiles[i1][j1];
				tiles[i1][j1] = aux;
			}
		} else if (tiles[3][3] == 0) {
			if (tiles[i1][j1 - 1] == tiles[i2][j2]
					|| tiles[i1 - 1][j1] == tiles[i2][j2]) {
				aux = tiles[i2][j2];
				tiles[i2][j2] = tiles[i1][j1];
				tiles[i1][j1] = aux;
			}
		} else if (tiles[0][0] == 0) {
			if (tiles[i1 + 1][j1] == tiles[i2][j2]
					|| tiles[i1][j1 + 1] == tiles[i2][j2]) {
				aux = tiles[i2][j2];
				tiles[i2][j2] = tiles[i1][j1];
				tiles[i1][j1] = aux;
			}

		} else if (tiles[1][1] == 0 || tiles[1][2] == 0 || tiles[2][1] == 0
				|| tiles[2][2] == 0) {
			if (tiles[i1 + 1][j1] == tiles[i2][j2]
					|| tiles[i1 - 1][j1] == tiles[i2][j2]
					|| tiles[i1][j1 - 1] == tiles[i2][j2]
					|| tiles[i1][j1 + 1] == tiles[i2][j2]) {
				aux = tiles[i2][j2];
				tiles[i2][j2] = tiles[i1][j1];
				tiles[i1][j1] = aux;
			}
		} else if (tiles[0][1] == 0 || tiles[0][2] == 0) {
			if (tiles[i1 + 1][j1] == tiles[i2][j2]
					|| tiles[i1][j1 + 1] == tiles[i2][j2]
					|| tiles[i1][j1 - 1] == tiles[i2][j2]) {
				aux = tiles[i2][j2];
				tiles[i2][j2] = tiles[i1][j1];
				tiles[i1][j1] = aux;
			}
		}

		else // if(tiles[13]==0||tiles[14]==0)
		if (tiles[i1][j1 + 1] == tiles[i2][j2]
				|| tiles[i1][j1 - 1] == tiles[i2][j2]
				|| tiles[i1 - 1][j1] == tiles[i2][j2]) {
			aux = tiles[i2][j2];
			tiles[i2][j2] = tiles[i1][j1];
			tiles[i1][j1] = aux;
		}

		/*
		 * if (tiles[i][j]==n) { aux=tiles[i][j]; tiles[i][j]=tiles[i1][j1];
		 * tiles[i1][j1]=aux;
		 * 
		 * }
		 */

		// }
		// }

		return tiles;

	}

	public static int[][] display(int[][] tiles) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {

				System.out.print(tiles[i][j] + " ");
			}
			System.out.println();
		}
		return tiles;

	}

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

	private void reset() {
		SlidingPuzzle p = new SlidingPuzzle();
	}

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

	private static int[] inverseArray(int movements[]) {
		int aux = 0;
		for (int i = 0; i < movements.length / 2; i++) {
			aux = movements[i];
			movements[i] = movements[movements.length - 1 - i];
			movements[movements.length - 1 - i] = aux;
		}
		return movements;
	}

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

	}

	public static void main(String[] args) {
		SlidingPuzzle p = new SlidingPuzzle();

	}

}