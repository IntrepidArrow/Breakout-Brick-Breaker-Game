package ca.mcgill.ecse223.block.view;


import java.awt.EventQueue;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

public class GameSettingsPage extends JFrame{

	private static final long serialVersionUID = -4426310869335015542L;
	// initialize text fields

	private JTextField ballSpeedIncreaseFactorField;
	private JTextField ballMinSpeedYField;
	private JTextField ballMinSpeedXField;
	private JTextField paddleMaxLengthField;

	private JTextField paddleMinLengthField;
	private JTextField nrBlocksPerLevelField;
	private JTextField nrLevelsField;
	private JTextField gameNameField;

	// remove all model import
//	  public static void main(String[] args) {
//		  Block223 block223 =  Block223Application.getBlock223(); 
//		  Admin currentUserRole = new Admin("albert", block223);
//	  Block223Application.setCurrentUserRole(currentUserRole); 
//	  Game game = null;
//	  Block223Application.setCurrentGame(game); 
//	  game = new Game("revvy", 10, (Admin) currentUserRole, 5 ,4, 5.0, 6,3, block223);
//	  Block223Application.setCurrentGame(game); 
//	  int level = 1; 
//	  int nrLevelCreated = 2; 
//	  try { 
//		  Block223Controller.setGameDetails(nrLevelCreated, 10, 5 ,4, 5.0 , 6,3); 
//		  Block223Controller.addBlock(200, 30, 10, 40);
//		  Block223Controller.addBlock(40, 30, 200, 65);
//		  Block223Controller.positionBlock(1, level, 3, 4);
//		  Block223Controller.positionBlock(2, level, 6, 10);
//	  }catch (InvalidInputException e) { // TODO Auto-generated catch block 
//		  //error = e.getMessage(); 
//		  e.printStackTrace(); 
//		  }
//	  
//	  
//	  EventQueue.invokeLater(new Runnable() { 
//		  public void run() { 
//			  try { 
//				  HallOfFame window = new hall(); 
//				  window.setVisible(true); 
//				  } catch (Exception e) {
//				  e.printStackTrace(); 
//				  } 
//			  } 
//		  }); 
//	  }



	/**
	 * Creates the frame.
	 */
	public GameSettingsPage() {
		initComponent();
	}

	/**
	 * Initialize the contents of the gameSettingsFrame.
	 */
	private void initComponent() {

		setBounds(100, 100, 548, 601);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Code to open window in center of the screen, despite dimensions of the monitor application is run on
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		JLabel gameNameLabel = new JLabel("Game Name:");

		JLabel nrLevelsLabel= new JLabel("Number Of Levels:");

		JLabel nrBlocksPerLevelLabel = new JLabel("Number of blocks per level:");

		JLabel paddleMinLengthLabel = new JLabel("Paddle min Length: ");

		JLabel paddleMaxLengthLabel = new JLabel("Paddle max length");

		JLabel ballMinSpeedXLabel = new JLabel("Ball min speed X:");

		JLabel ballMinSpeedYLabel = new JLabel("Ball min speed Y:");

		JLabel ballSpeedIncreaseFactorLabel = new JLabel("Speed increase factor:");

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveBtnActionPerformed(evt);
			}
		});
		btnSave.setForeground(Color.BLACK);

		JButton btnEditLevels = new JButton("Edit Levels");
		btnEditLevels.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				editLevelsBtnActionPerformed(evt);
			}
		});


		JButton btnEditBlocks = new JButton("Edit Blocks");
		btnEditBlocks.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editBlocksBtnActionPerformed(evt);
			}
		});

		ballSpeedIncreaseFactorField = new JTextField();
		ballSpeedIncreaseFactorField.setColumns(10);

		ballMinSpeedYField = new JTextField();
		ballMinSpeedYField.setColumns(10);

		ballMinSpeedXField = new JTextField();
		ballMinSpeedXField.setColumns(10);

		paddleMaxLengthField = new JTextField();
		paddleMaxLengthField.setColumns(10);

		paddleMinLengthField = new JTextField();
		paddleMinLengthField.setColumns(10);

		nrBlocksPerLevelField = new JTextField();
		nrBlocksPerLevelField.setColumns(10);

		nrLevelsField = new JTextField();
		nrLevelsField.setColumns(10);

		gameNameField = new JTextField();
		gameNameField.setColumns(10);

		JLabel lblGameSettingsPage = new JLabel("Game Settings Page");
		lblGameSettingsPage.setForeground(Color.BLACK);
		lblGameSettingsPage.setFont(new Font("Lucida Grande", Font.PLAIN, 22));

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminHomePage(Block223Application.getCurrentUserRole().toString()).setVisible(true);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(ballSpeedIncreaseFactorLabel)
												.addComponent(ballMinSpeedYLabel)
												.addComponent(ballMinSpeedXLabel)
												.addComponent(paddleMaxLengthLabel)
												.addComponent(paddleMinLengthLabel)
												.addComponent(nrBlocksPerLevelLabel)
												.addComponent(nrLevelsLabel)
												.addComponent(gameNameLabel))
										.addGap(21)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(gameNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(nrLevelsField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(nrBlocksPerLevelField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(paddleMinLengthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(paddleMaxLengthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(ballMinSpeedXField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(ballMinSpeedYField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(ballSpeedIncreaseFactorField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(141)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(btnEditLevels, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
														.addComponent(btnEditBlocks, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
												.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))))
						.addGap(156))
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnBack)
						.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
						.addComponent(lblGameSettingsPage, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addGap(139))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(27)
										.addComponent(lblGameSettingsPage))
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnBack)))
						.addGap(39)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(gameNameLabel)
								.addComponent(gameNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(nrLevelsLabel)
								.addComponent(nrLevelsField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(nrBlocksPerLevelLabel)
								.addComponent(nrBlocksPerLevelField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(paddleMinLengthLabel)
								.addComponent(paddleMinLengthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(paddleMaxLengthLabel)
								.addComponent(paddleMaxLengthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(ballMinSpeedXLabel)
								.addComponent(ballMinSpeedXField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(ballMinSpeedYLabel)
								.addComponent(ballMinSpeedYField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(ballSpeedIncreaseFactorLabel)
								.addComponent(ballSpeedIncreaseFactorField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(25)
						.addComponent(btnSave)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnEditLevels)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnEditBlocks)
						.addContainerGap(77, Short.MAX_VALUE))
				);
		getContentPane().setLayout(groupLayout);
		refreshData();
	}

	private void refreshData() {
		//errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			// populate page with data
			gameNameField.setText("");
			nrLevelsField.setText("");
			nrBlocksPerLevelField.setText("");
			paddleMinLengthField.setText("");
			paddleMaxLengthField.setText("");
			ballMinSpeedXField.setText("");
			ballMinSpeedYField.setText("");
			ballSpeedIncreaseFactorField.setText("");
			TOGame game = null;
			try {
				game = Block223Controller.getCurrentDesignableGame();
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}

			gameNameField.setText(game.getName()); 
			nrLevelsField.setText(Integer.toString(Math.max(game.getNrLevels(),1)));
			nrBlocksPerLevelField.setText(Integer.toString(game.getNrBlocksPerLevel())); 
			paddleMinLengthField.setText(Integer.toString(game.getMinPaddleLength())); 
			paddleMaxLengthField.setText(Integer.toString(game.getMaxPaddleLength())); 
			ballMinSpeedXField.setText(Integer.toString(game.getMinBallSpeedX()));
			ballMinSpeedYField.setText(Integer.toString(game.getMinBallSpeedY())); 
			ballSpeedIncreaseFactorField.setText(Double.toString(game.getBallSpeedIncreaseFactor()));

		}
	}
	private String error = null;
	private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {

		error = null;
		// call the controller

		try {


			Block223Controller.updateGame(
					gameNameField.getText(), Integer.parseInt(nrLevelsField.getText()), Integer.parseInt(nrBlocksPerLevelField.getText()),
					Integer.parseInt(ballMinSpeedXField.getText()), Integer.parseInt(ballMinSpeedYField.getText()),
					Double.parseDouble(ballSpeedIncreaseFactorField.getText()), Integer.parseInt(paddleMaxLengthField.getText()),
					Integer.parseInt(paddleMinLengthField.getText()));
			Block223Controller.saveGame();
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// update visuals right after the changes 
		refreshData();

	}

	private void editLevelsBtnActionPerformed(java.awt.event.ActionEvent evt) {
		dispose();
		// create a new instance of edit levels frame and make it visible 
		new EditLevel(); 
	
}

	private void editBlocksBtnActionPerformed(java.awt.event.ActionEvent evt) {
		dispose();
		new EditBlocksPage();
	}
}
