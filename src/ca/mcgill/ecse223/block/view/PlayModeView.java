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
import ca.mcgill.ecse223.block.view.PlayModeListener;
import ca.mcgill.ecse223.block.view.PlayModeView;

public class PlayModeView extends JFrame implements Block223PlayModeInterface {

	PlayModeListener listener;
	JTextArea gameArea;
	
	public PlayModeView() {
		createAndShowGUI();
	}

	/**
	 * Creating GUI
	 */
	private void createAndShowGUI() {
		// Create and set up the window.
		this.setTitle("Block223 PlayMode Example");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add components to window pane
		this.addComponentsToPane();

		// Display the window.
		this.pack();
		this.setVisible(true);
	}

	private void addComponentsToPane() {

		JButton button = new JButton("Start Game");

		gameArea = new JTextArea();
		gameArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(gameArea);
		scrollPane.setPreferredSize(new Dimension(375, 125));

		getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(button, BorderLayout.PAGE_END);

		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button.setVisible(false);
				// initiating a thread to start listening to keyboard inputs
				listener = new PlayModeListener();
				Runnable r1 = new Runnable() {
					@Override
					public void run() {
						// in the actual game, add keyListener to the game window
						gameArea.addKeyListener(listener);
					}
				};
				Thread t1 = new Thread(r1);
				t1.start();
				// to be on the safe side use join to start executing thread t1 before executing
				// the next thread
				try {
					t1.join();
				} catch (InterruptedException e1) {
				}

				// initiating a thread to start the game loop
				Runnable r2 = new Runnable() {
					@Override
					public void run() {
						try {
							Block223Controller.startGame(PlayModeView.this);
							button.setVisible(true);
						} catch (InvalidInputException e) {
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

	}

	@Override
	public void endGame(int nrOfLives, TOHallOfFameEntry hof) {
		// TODO Auto-generated method stub

	}

}
