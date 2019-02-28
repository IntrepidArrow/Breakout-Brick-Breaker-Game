package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGridCell;

public class EditLevel {
	
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
			// elements for error message
			errorMessage = new JLabel();
			errorMessage.setForeground(Color.RED);
			
			//elements for levels
			levelLabel = new JLabel();
			levelLabel.setText("Levels");
			levelList = new JComboBox<String>(new String[0]);
			addLevelButton = new JButton();
			removeLevelButton = new JButton();
			
			//elements for block play area
			blockAreaLabel = new JLabel();
			blockAreaLabel.setText("Play Area");
			
			//elements for blocks available
			blockLabel = new JLabel();
			blockLabel.setText("Levels");
			blockList = new JComboBox<String> (new String[0]);
			positionBlockButton = new JButton();
			moveBlockButton = new JButton();
			removeBlockButton = new JButton();
			horizontalPositionTextField = new JTextField();
			verticalPositionTextField = new JTextField();
			
			
		/*
		 * // global settings setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		 * setTitle("Block223 Group29 Game");
		 */
			
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
			
			// horizontal line elements
			JSeparator verticalLineLeft = new JSeparator();
			JSeparator verticalLineRight = new JSeparator();
			
			// layout
			// layout
			GroupLayout layout = new GroupLayout(getContentPane());
			getContentPane().setLayout(layout);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			layout.setHorizontalGroup(
					layout.createParallelGroup()
					
					
					);
			
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
		

