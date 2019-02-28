package ca.mcgill.ecse223.block.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ca.mcgill.ecse223.block.controller.InvalidInputException; 

public class GameSettingsPage  extends JFrame{
	
	// step 1: UI elements for game settings page need to add the error messages
	// frame 
	
	private JFrame gameSettingsFrame;
	// text fields 
	private JTextField gameNameField;
	private JTextField nrOfLevelsField;
	private JTextField nrBlocksPerLevelField; 
	private JTextField paddleMinLengthField;
	private JTextField paddleMaxLengthField; 
	private JTextField ballMinSpeedXField; 
	private JTextField ballMinSpeedYField; 
	private JTextField ballSpeedIncreaseFactorField; 
	
	// JLabels 
	
	private JLabel gameNameLabel;
	private JLabel nrOfLevelsLabel; 
	private JLabel nrBlocksPerLevelLabel; 
	private JLabel paddleMinLengthLabel; 
	private JLabel paddleMaxLengthLabel; 
	private JLabel ballMinSpeedXLabel; 
	private JLabel ballMinSpeedYLabel; 
	private JLabel ballSpeedIncreaseFactorLabel; 
	
	// JButtons
	
	private JButton saveButton;
	private JButton editLevelsButton;
	private JButton editBlocksButton; 
	
	// step 2: initialize all of these inside initcomponent which is a static method 
	
	private void initComponent() {
		
		// initialization of JLabels amd the frame
		gameSettingsFrame = new JFrame(); 
		
		gameNameLabel = new JLabel(); 
		gameNameLabel.setText("Game Name:"); 
		nrOfLevelsLabel = new JLabel(); 
		nrOfLevelsLabel.setText("Number of Levels:"); 
		nrBlocksPerLevelLabel = new JLabel(); 
		nrBlocksPerLevelLabel.setText("number of blocks per level:"); 
		paddleMinLengthLabel = new JLabel(); 
		paddleMinLengthLabel.setText("paddle minimum length"); 
		paddleMaxLengthLabel = new JLabel(); 
		paddleMaxLengthLabel.setText("paddle maximum length"); 
		ballMinSpeedXLabel = new JLabel(); 
		ballMinSpeedXLabel.setText("ball minimum speed X"); 
		ballMinSpeedYLabel = new JLabel();
		ballMinSpeedYLabel.setText("ball minimum speed Y"); 
		ballSpeedIncreaseFactorLabel = new JLabel(); 
		ballSpeedIncreaseFactorLabel.setText("ball speed increase factor"); 
		
		// initialization of JTextFields 
		
		gameNameField = new JTextField(); 
		nrOfLevelsField = new JTextField(); 
		nrBlocksPerLevelField = new JTextField(); 
		paddleMinLengthField = new JTextField(); 
		paddleMaxLengthField = new JTextField(); 
		ballMinSpeedXField = new JTextField(); 
		ballMinSpeedYField = new JTextField(); 
		ballSpeedIncreaseFactorField = new JTextField(); 
		
		// initialization of JButton elements  
		
		saveButton = new JButton(); 
		saveButton.setText("save changes"); 
		editLevelsButton = new JButton(); 
		editLevelsButton.setText("edit levels"); 
		editBlocksButton = new JButton(); 
		editBlocksButton.setText("edit blocks"); 
		
		// set up action listeners 
		
		// TODO implement saveButtonActionPerformed 
		// TODO ask the group how to implement the two other actions listeners 
		
		saveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveButtonActionPerformed(evt);
				}
				});
		
		editLevelsButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
			editLevelsButtonActionPerformed(evt);
			}
			});
		
		editBlocksButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		    editBlocksButtonActionPerformed(evt);
			}
			});
		
		// step 3: Create the layout 
		
		// I want to link all my text fields to the save button and all my labels to the textFields
		
		
		
	}
	

	private void refreshData() {
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
		// populate page with data
		
		gameNameField.setText("");
		nrOfLevelsField.setText("");
		nrBlocksPerLevelField.setText("");
		paddleMinLengthField.setText("");
		paddleMaxLengthField.setText("");
		ballMinSpeedXField.setText("");
		ballMinSpeedYField.setText("");
		ballSpeedIncreaseFactorField.setText("");
		
		}
		
		pack(); 
	}
	
	
	// step 5: 
	
	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = null;
		// call the controller
		try {
			
		BtmsController.updateGame(gameNameField.getText(), nrOfLevelsField.getText(), nrBlocksPerLevelField.getText(), ballMinSpeedXField.getText(), ballMinSpeedYField.getText(), ballSpeedIncreaseFactorField.getText(), paddleMinLengthField.getText(), paddleMaxLengthField.getText());
		} catch (InvalidInputException e) {
		error = e.getMessage();
		}
		// update visuals
		refreshData();
		}
	
	// method create frame 
	
	public static void createEditLevelsFrame() {
		
		
		// copy paste the window of edit levels
	}
	
	public static void createEditBlocksFrame() { 
		
		// copy paste the window of edit blocks
	}
	
	 // should call createEditLevels frame
	private void editLevelsButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = null; 
		
		try { 
			
			
		}
	}
	
	// should call createEditBlocksFrame
	private void editBlocksButtonActionPerformed(java.awt.event.ActionEvent evt) {
		
		
	}
	
	
	

}
