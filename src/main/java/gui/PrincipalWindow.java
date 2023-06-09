package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logic.SlidingPuzzle;
import logic.util.Logger;

/**
 * This class represents a sliding puzzle game GUI.
 *
 * @author Pedro Zahonero Mangas
 * @author Pablo Garcia Fernandez
 * @version 1.0
 * @since 2023-05-15
 */
public class PrincipalWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SlidingPuzzle puzzle;
	private JButton[] buttons;
	private JPanel pnBoard;
	private JPanel pnGame;
	private JPanel pnButtons;
	private JButton btReset;
	private JButton btSolve;
	private JPanel pnWin;
	private JLabel lbCongratulations;
	private JLabel lbText;

	/**
	 * Create the frame.
	 */
	public PrincipalWindow(SlidingPuzzle puzzle) {
		Logger.getInstance().log(Logger.INFO,
				"Creation of instance of PrincipalWindow.java");
		this.puzzle = puzzle;
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 425);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		createBoard();
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnGame(), "pnGame");
		contentPane.add(getPnWin(), "pnWin");
	}

	/**
	 * Creates all the tiles of the board
	 */
	private void addTiles() {
		Logger.getInstance().log(Logger.RUNNING,
				"Class: PrincipalWindow.java , method: addTiles()");
		int n = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (puzzle.getBoard()[i][j] == 0) {
					buttons[n] = new JButton();
					buttons[n].setEnabled(false);
					buttons[n].setBackground(new Color(255, 255, 255));
					buttons[n].setForeground(new Color(255, 255, 255));
					buttons[n].setFont(new Font("Tahoma", Font.PLAIN, 30));
					pnBoard.add(buttons[n]);
				} else {
					buttons[n] = new JButton("" + puzzle.getBoard()[i][j]);
					buttons[n].setFont(new Font("Tahoma", Font.PLAIN, 30));
					buttons[n].setForeground(new Color(255, 255, 255));
					buttons[n].setBackground(new Color(255, 128, 64));
					buttons[n].setBorder(new LineBorder(new Color(0, 0, 0)));
					pnBoard.add(buttons[n]);
					buttons[n].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int n = Integer.parseInt(((JButton) e.getSource())
									.getText().toString());
							puzzle.move(n);
							updatePanel();
						}
					});
				}
				n++;
			}
		}
	}

	/**
	 * Create the board with the tiles
	 */
	private void createBoard() {
		Logger.getInstance().log(Logger.RUNNING,
				"Class: PrincipalWindow.java , method: createTiles()");
		puzzle.shuffle();
		buttons = new JButton[16];
		pnBoard = new JPanel();
		pnBoard.setBackground(new Color(0, 0, 0));
		pnBoard.setLayout(new GridLayout(4, 4, 0, 0));
		addTiles();
		getPnGame().add(pnBoard, BorderLayout.CENTER);
	}

	/**
	 * Updates the board
	 */
	private void updatePanel() {
		Logger.getInstance().log(Logger.RUNNING,
				"Class: PrincipalWindow.java , method: updatePanel()");
		for (Component component : pnBoard.getComponents()) {
			pnBoard.remove(component);
		}
		pnBoard.revalidate();
		pnBoard.repaint();
		addTiles();
		if (puzzle.win()) {
			CardLayout layout = (CardLayout) getContentPane().getLayout();
			layout.show(getContentPane(), "pnWin");
		}
	}

	/**
	 * The panel where is the game
	 * 
	 * @return The panel
	 */
	private JPanel getPnGame() {
		Logger.getInstance().log(Logger.RUNNING,
				"Class: PrincipalWindow.java , method: getPnGame()");
		if (pnGame == null) {
			pnGame = new JPanel();
			pnGame.setLayout(new BorderLayout(0, 0));
			pnGame.add(getPnButtons(), BorderLayout.SOUTH);
		}
		return pnGame;
	}

	/**
	 * The panel where are the buttons of solve or reset
	 * 
	 * @return The Panel
	 */
	private JPanel getPnButtons() {
		Logger.getInstance().log(Logger.RUNNING,
				"Class: PrincipalWindow.java , method: getPnButtons()");
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBackground(Color.BLACK);
			pnButtons.add(getBtReset());
			pnButtons.add(getBtSolve());
		}
		return pnButtons;
	}

	/**
	 * The button that reset the game
	 * 
	 * @return The button
	 */
	public JButton getBtReset() {
		Logger.getInstance().log(Logger.RUNNING,
				"Class: PrincipalWindow.java , method: getBtReset()");
		if (btReset == null) {
			btReset = new JButton("Reset");
			btReset.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					puzzle.reset();
					updatePanel();
				}
			});
			btReset.setForeground(Color.WHITE);
			btReset.setBackground(new Color(255, 128, 64));
		}
		return btReset;
	}

	/**
	 * The button that solve the game
	 * 
	 * @return The button
	 */
	public JButton getBtSolve() {
		Logger.getInstance().log(Logger.RUNNING,
				"Class: PrincipalWindow.java , method: getBtSolve()");
		if (btSolve == null) {
			btSolve = new JButton("Solve");
			btSolve.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					puzzle.solve();
					updatePanel();
				}
			});
			btSolve.setForeground(Color.WHITE);
			btSolve.setBackground(new Color(255, 128, 64));
		}
		return btSolve;
	}

	/**
	 * The panel of the board where are the tiles
	 * 
	 * @return The panel
	 */
	public JPanel getPnBoard() {
		Logger.getInstance().log(Logger.RUNNING,
				"Class: PrincipalWindow.java , method: getPnBoard()");
		return pnBoard;
	}

	/**
	 * The panel where congratulates you when you win the game
	 * 
	 * @return The panel
	 */
	private JPanel getPnWin() {
		Logger.getInstance().log(Logger.RUNNING,
				"Class: PrincipalWindow.java , method: getPnWin()");
		if (pnWin == null) {
			pnWin = new JPanel();
			pnWin.setBackground(new Color(0, 0, 0));
			pnWin.setLayout(new GridLayout(2, 1, 0, 0));
			pnWin.add(getLbCongratulations());
			pnWin.add(getLbText());
		}
		return pnWin;
	}

	/**
	 * The label of congratulations, it is shown when the the player wins
	 * 
	 * @return The label
	 */
	private JLabel getLbCongratulations() {
		Logger.getInstance().log(Logger.RUNNING,
				"Class: PrincipalWindow.java , method: getLbCongratulations()");
		if (lbCongratulations == null) {
			lbCongratulations = new JLabel("Congratulations!");
			lbCongratulations.setVerticalAlignment(SwingConstants.BOTTOM);
			lbCongratulations.setHorizontalAlignment(SwingConstants.CENTER);
			lbCongratulations.setFont(new Font("Tahoma", Font.PLAIN, 37));
			lbCongratulations.setForeground(new Color(255, 128, 64));
			lbCongratulations.setAutoscrolls(true);
			lbCongratulations.setVerifyInputWhenFocusTarget(true);
		}
		return lbCongratulations;
	}

	/**
	 * Label with a little text, it is shown when the the player wins
	 * 
	 * @return The label
	 */
	private JLabel getLbText() {
		Logger.getInstance().log(Logger.RUNNING,
				"Class: PrincipalWindow.java , method: getLbText()");
		if (lbText == null) {
			lbText = new JLabel("You have won the SlidingPuzzle");
			lbText.setVerticalAlignment(SwingConstants.TOP);
			lbText.setHorizontalAlignment(SwingConstants.CENTER);
			lbText.setFont(new Font("Tahoma", Font.PLAIN, 22));
			lbText.setForeground(new Color(255, 128, 64));
		}
		return lbText;
	}
}
