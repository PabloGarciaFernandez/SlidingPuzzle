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

public class PrincipalWindow extends JFrame {

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
		this.puzzle = puzzle;
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 425);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		createTiles();
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnGame(), "pnGame");
		contentPane.add(getPnWin(), "pnWin");
	}

	private void addTiles() {
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

	private void createTiles() {
		puzzle.shuffle();
		// Create an array of buttons.
		buttons = new JButton[16];
		// Add the buttons to a panel.
		pnBoard = new JPanel();
		pnBoard.setBackground(new Color(0, 0, 0));
		pnBoard.setLayout(new GridLayout(4, 4, 0, 0));
		addTiles();
		// Add the panel to the content pane.
		getPnGame().add(pnBoard, BorderLayout.CENTER);
	}

	private void updatePanel() {
		for (Component component : pnBoard.getComponents()) {
			pnBoard.remove(component);
		}
		// Revalidate and repaint the panel.
		pnBoard.revalidate();
		pnBoard.repaint();
		addTiles();
		if (puzzle.win()) {
			CardLayout layout = (CardLayout) getContentPane().getLayout();
			layout.show(getContentPane(), "pnWin");

		}

		// createTiles();
	}

	private JPanel getPnGame() {
		if (pnGame == null) {
			pnGame = new JPanel();
			pnGame.setLayout(new BorderLayout(0, 0));
			pnGame.add(getPnButtons(), BorderLayout.SOUTH);
		}
		return pnGame;
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBackground(Color.BLACK);
			pnButtons.add(getBtReset());
			pnButtons.add(getBtSolve());
		}
		return pnButtons;
	}

	private JButton getBtReset() {
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

	private JButton getBtSolve() {
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

	private JPanel getPnWin() {
		if (pnWin == null) {
			pnWin = new JPanel();
			pnWin.setBackground(new Color(0, 0, 0));
			pnWin.setLayout(new GridLayout(2, 1, 0, 0));
			pnWin.add(getLbCongratulations());
			pnWin.add(getLbText());
		}
		return pnWin;
	}

	private JLabel getLbCongratulations() {
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

	private JLabel getLbText() {
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
