package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;
import ca.mcgill.ecse223.block.view.PlayModeListener;
import javax.swing.JPanel;
import java.awt.Color;

public class PlayModeView extends JFrame implements Block223PlayModeInterface {

	PlayModeListener listener;
	private PlayModeVisualizer playModeVisualizer; // extends JPanel
	String username;
	boolean isTestGame;

	public PlayModeView(boolean isTest) {
		createAndShowGUI();
		this.isTestGame = isTest;
	}

	/**
	 * Creating GUI
	 */
	private void createAndShowGUI() {
		// Create and set up the window.
		this.setTitle("Block223 PlayMode");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		// Add components to window pane
		this.addComponentsToPane();

		// Display the window.
		this.pack();
		this.setVisible(true);
	}

	private void addComponentsToPane() {

		JButton button = new JButton("Start Game");
		getContentPane().add(button, BorderLayout.PAGE_END);

		playModeVisualizer = new PlayModeVisualizer();
		playModeVisualizer.setName("Block223 PlayMode");
		playModeVisualizer.setMaximumSize(new Dimension(390, 390));
		playModeVisualizer.setMinimumSize(new Dimension(390, 390));
		playModeVisualizer.setBackground(Color.WHITE);
		playModeVisualizer.setPreferredSize(new Dimension(390, 390));
		playModeVisualizer.setSize(new Dimension(390, 390));
		playModeVisualizer.setFocusable(true);
		getContentPane().add(playModeVisualizer, BorderLayout.NORTH);

		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button.setVisible(false);
				// initiating a thread to start listening to keyboard inputs
				listener = new PlayModeListener();
				Runnable r1 = new Runnable() {
					@Override
					public void run() {
						// in the actual game, add keyListener to the game window
						playModeVisualizer.addKeyListener(listener);
					}
				};
				Thread t1 = new Thread(r1);
				t1.start();
				// to be on the safe side use join to start executing thread t1 before executing
				// the next thread
				try {
					t1.join();
				} catch (InterruptedException e1) {
					System.out.println("t1 interrupted");
				}

				// initiating a thread to start the game loop
				Runnable r2 = new Runnable() {
					@Override
					public void run() {
						try {
							System.out.println("starting");
							if (isTestGame) {
								Block223Controller.testGame(PlayModeView.this);
							} else {
								Block223Controller.startGame(PlayModeView.this);
							}
							button.setVisible(true);
						} catch (InvalidInputException e) {
							System.out.println("t2 interrupted");
						}
					}
				};
				Thread t2 = new Thread(r2);
				t2.start();
			}
		});
	}

	@Override
	public String takeInputs() {
		if (listener == null) {
			return "";
		}
		return listener.takeInputs();
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		try {
			TOCurrentlyPlayedGame thisGame = Block223Controller.getCurrentPlayableGame();
			playModeVisualizer.refreshDrawing(thisGame);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void endGame(int nrOfLives, TOHallOfFameEntry hof) {
		// TODO Auto-generated method stub

	}

}
