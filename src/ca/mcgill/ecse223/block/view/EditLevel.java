package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.util.HashMap;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGridCell;

public class EditLevel extends JFrame {
	
	private static final long serialVersionUID = -4426310869376315542L;
	
		// UI elements
		private JLabel errorMessage;
		private JButton backButton;
		private JButton logoutButton;
		private JLabel usernameLabel;
		
		// Levels
		private JLabel levelLabel;
		private JComboBox<String> levelList; // maybe change to a table later
		private JButton addLevelButton;
		private JButton removeLevelButton;
		
		//block play area
		private JLabel blockAreaLabel;
		
		//Blocks Available
		private JLabel blockLabel;
		private JComboBox<String> blockList; // maybe change to a table later
		private JButton positionBlockButton;
		private JButton moveBlockButton;
		private JButton removeBlockButton;
		private JTextField horizontalPositionTextField;
		private JTextField verticalPositionTextField;
		
		//Data elements
		private String error = null;
		//levels
		private HashMap<Integer, Integer> levels;
		//blocks
		private HashMap<Integer, TOBlock> blocksAvailable;
		private HashMap<Integer, TOGridCell> blocksInArea;
		
		/** Creates new form BtmsPage */
		public EditLevel() {
			initComponents();
			refreshData();
		}
		
		
		private void initComponents() {
			
			//elements for UI
			logoutButton = new JButton();
			usernameLabel = new JLabel();
			usernameLabel.setText("username");
			backButton = new JButton();
			
			// elements for error message
			errorMessage = new JLabel();
			errorMessage.setText("error");
			errorMessage.setForeground(Color.RED);
			
			//elements for levels
			levelLabel = new JLabel();
			levelLabel.setText("Levels");
			String[] levelListarray = {"level 1" , "level 2"};
			levelList = new JComboBox<String>( levelListarray);
			addLevelButton = new JButton();
			addLevelButton.setText("Add Level");
			removeLevelButton = new JButton();
			removeLevelButton.setText("Remove Level");
			
			//elements for block play area
			blockAreaLabel = new JLabel();
			blockAreaLabel.setText("Play Area");
			
			//elements for blocks available
			blockLabel = new JLabel();
			blockLabel.setText("Levels");
			String[] blocklistarray = {"block1, red" , "block2, rblue"};
			blockList = new JComboBox<String> (blocklistarray);
			positionBlockButton = new JButton();
			positionBlockButton.setText("Position Block");
			moveBlockButton = new JButton();
			moveBlockButton.setText("Move Block");
			removeBlockButton = new JButton();
			removeBlockButton.setText("Remove Block");
			horizontalPositionTextField = new JTextField();
			horizontalPositionTextField.setToolTipText("horizontal position of block");
			verticalPositionTextField = new JTextField();
			
			
		
		  // global settings 
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		  setTitle("Block223 Group29 Game");
		 
			
			//listeners for levels
			addLevelButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					addLevelButtonActionPerformed(evt);
				}
			});
			
			removeLevelButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					removeLevelButtonActionPerformed(evt);
				}
			});
			
			//listeners for blocks available
			positionBlockButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					positionBlockButtonActionPerformed(evt);
				}
			});
			
			moveBlockButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					moveBlockButtonActionPerformed(evt);
				}
			});
			
			removeBlockButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					removeBlockButtonActionPerformed(evt);
				}
			});
			
			
			
			// horizontal/vertical line elements
			JSeparator verticalLineLeft = new JSeparator();
			JSeparator verticalLineRight = new JSeparator();
			JSeparator horizontalLineLevel = new JSeparator();
			JSeparator horizontalLineBlock = new JSeparator();
			
			// layout
			GroupLayout layout = new GroupLayout(getContentPane());
			getContentPane().setLayout(layout);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup())
					.addGroup(layout.createParallelGroup()
						.addComponent(backButton)
						.addComponent(levelLabel)
						.addComponent(levelList)
						.addComponent(addLevelButton)
						//.addComponent(horizontalLineLevel)
						.addComponent(removeLevelButton))
					.addComponent(verticalLineLeft)
					.addGroup(layout.createParallelGroup()
						.addComponent(blockAreaLabel)
						//.add(PlayArea)
						)
					.addComponent(verticalLineRight)
					.addGroup(layout.createParallelGroup()
							.addComponent(usernameLabel)
							.addComponent(blockLabel)
							.addComponent(blockList)
							.addGroup(layout.createSequentialGroup()
									.addComponent(horizontalPositionTextField)
									.addComponent(verticalPositionTextField))
							.addComponent(positionBlockButton)
							.addComponent(horizontalLineBlock)
							.addComponent(moveBlockButton)
							.addComponent(removeBlockButton))
					
					
					);
			
			layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {moveBlockButton, positionBlockButton});
			layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {moveBlockButton, removeBlockButton});
			layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addLevelButton, removeLevelButton});
			layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addLevelButton, horizontalLineLevel});
			layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {moveBlockButton, horizontalLineBlock});
			
			
			layout.setVerticalGroup(
					layout.createParallelGroup()
						/*
						 * .addComponent(verticalLineRight) .addComponent(verticalLineLeft)
						 */
					.addComponent(errorMessage)
					.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
							.addComponent(backButton)
							.addComponent(usernameLabel))
						
						.addGroup(layout.createParallelGroup()
							.addComponent(levelLabel)
							.addComponent(blockAreaLabel)
							.addComponent(blockLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(levelList)
								//.addComponent(playAreaList)
								.addComponent(blockList))
						.addGroup(layout.createParallelGroup()
								.addComponent(addLevelButton)
								.addComponent(positionBlockButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(horizontalLineBlock, 200, 200, 400)
								.addComponent(horizontalLineLevel, 200, 200, 400))
						.addGroup(layout.createParallelGroup()
								.addComponent(removeLevelButton)
								.addComponent(moveBlockButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(horizontalPositionTextField)
								.addComponent(verticalPositionTextField))
						.addComponent(removeBlockButton)
						)
					);
			
			//pack();
			
		}
		
		private void refreshData() {
		}
		
		// add level button
		private void addLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {
			
		}
		
		// remove level button
		private void removeLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {
			
		}
		
		//position block button
		private void positionBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
			
		}
		
		//move block button
		private void moveBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
			
		}
		
		//remove block button
		private void removeBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
			
		}
		
		
		}
		

