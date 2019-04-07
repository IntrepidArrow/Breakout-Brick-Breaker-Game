package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;

public class EditLevel extends JFrame {

	private static final long serialVersionUID = -4426310869376315542L;

	// UI elements
	public JFrame editLevelWindow;
	private JLabel errorMessage;
	private JButton backButton;
	private JButton logoutButton;
	private JLabel usernameLabel;

	// Levels
	private JLabel levelLabel;
	private JComboBox<String> levelList; // maybe change to a table later
	// block play area
	private JLabel blockConfigurationLbl;
	private PlayAreaVisualizer playAreaVisualizer;
	private JComboBox<String> blockAreaList;

	// Blocks Available
	private JLabel lblBlocksAvailable;
	private JComboBox<String> blockList; // maybe change to private later, not used if playAreaVisualizer is used
	private JButton btnPositionNewBlock;
	private JButton btnMoveBlock;
	private JButton btnRemoveBlock;
	private JLabel lblHorizontal;
	private JTextField txtHorizontal;
	private JLabel lblVertical;
	private JTextField txtVertical;

	// Data elements
	private String error = null;
	// levels
	private HashMap<Integer, Integer> levels;
	// blocks
	private HashMap<Integer, TOBlock> blocksAvailable;
	// play area
	private HashMap<Integer, TOGridCell> blocksInArea;

	/**
	 * Launch the application.
	 */
	
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
//		  Block223Controller.addBlock(30, 30, 10, 40);
//		  Block223Controller.addBlock(40, 30, 10, 65);
//		  Block223Controller.positionBlock(1, level, 3, 4);
//		  Block223Controller.positionBlock(1, level, 4, 4);
//
//		  HashMap<Integer, TOGridCell> blocksInArea = new HashMap<Integer,TOGridCell>(); 
//		  int index = 0; 
//		  for (TOGridCell gridCell : Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level)){
//			  blocksInArea.put(index, gridCell); 
//			  blockList.addItem("#" + gridCell.getId()  + ", Points: " + gridCell.getPoints() + ", Color: " + gridCell.getRed());
//			  index++; 
//		  } 
//	  } catch (InvalidInputException e) { // TODO Auto-generated catch block 
//		  //error = e.getMessage(); 
//		  e.printStackTrace(); 
//		  }
//
//	  EventQueue.invokeLater(new Runnable() {
//		  public void run() {
//			  try {
//				  EditLevel window = new EditLevel();
//				  window.editLevelWindow.setVisible(true);
//			  } catch (Exception e) {
//				  e.printStackTrace();
//			  }
//		  }
//	  });
//
//	  }
		  
		  
	 
	
	/**
	 * Create the application.
	 */
	public EditLevel() {
		initComponents();
		refreshData();
		//refreshPlayAreaVisualizer(0);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initComponents() {

		editLevelWindow = new JFrame();
		editLevelWindow.setTitle("Play Area Configuration");
		editLevelWindow.setBounds(100, 100, 841, 629);
		editLevelWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editLevelWindow.setVisible(true);
		
		//Code to open window in center of the screen, despite dimensions of the monitor application is run on
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				editLevelWindow.setLocation(dim.width/2-editLevelWindow.getSize().width/2, dim.height/2-this.editLevelWindow.getHeight()/2);

		// elements for frame
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editLevelWindow.dispose();
				new GameSettingsPage().setVisible(true);
			}
		});
		
		
		// elements for levels
		levelLabel = new JLabel("Select a Level");
		levelList = new JComboBox();
		levelList.setMaximumRowCount(99);

		levelList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (levelList.getSelectedIndex() != 0 & levelList.getSelectedIndex() != -1)
					refreshPlayAreaVisualizer(levelList.getSelectedIndex());
			}
		});

		// elements for play area
		blockConfigurationLbl = new JLabel("Select a Grid Cell");
		blockAreaList = new JComboBox();

		
		  playAreaVisualizer = new PlayAreaVisualizer();
		  playAreaVisualizer.setMinimumSize(new Dimension(390, 390));
		  playAreaVisualizer.setBackground(Color.WHITE);
		 

		// elements for blocks available
		lblBlocksAvailable = new JLabel("Select a Block");

		blockList = new JComboBox();

		btnPositionNewBlock = new JButton("Position New Block");
		btnPositionNewBlock.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				positionBlockButtonActionPerformed(evt);
			}
		});

		btnMoveBlock = new JButton("Move Block");
		btnMoveBlock.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				moveBlockButtonActionPerformed(evt);
			}
		});

		btnRemoveBlock = new JButton("Remove Block");
		btnRemoveBlock.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeBlockButtonActionPerformed(evt);
			}
		});

		txtHorizontal = new JTextField();
		txtHorizontal.setToolTipText("Horizontal");
		txtHorizontal.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		txtHorizontal.setColumns(10);
		lblHorizontal = new JLabel("Horizontal");

		txtVertical = new JTextField();
		txtVertical.setToolTipText("Vertical");
		txtVertical.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		txtVertical.setColumns(10);
		lblVertical = new JLabel("Vertical");

		// layout
		GroupLayout groupLayout = new GroupLayout(editLevelWindow.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
								.createSequentialGroup().addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(39).addComponent(
												levelLabel))
										.addGroup(groupLayout.createSequentialGroup().addGap(
												22).addComponent(levelList, GroupLayout.PREFERRED_SIZE, 85,
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createParallelGroup(
										Alignment.LEADING, false).addGroup(
												groupLayout.createSequentialGroup().addGap(194).addComponent(
														blockConfigurationLbl).addGap(263)
														.addComponent(lblBlocksAvailable))
										.addGroup(groupLayout.createSequentialGroup().addGap(53)
												.addComponent(playAreaVisualizer, GroupLayout.PREFERRED_SIZE, 428,
												GroupLayout.PREFERRED_SIZE)
												//.addComponent(blockAreaList).addPreferredGap(ComponentPlacement.RELATED,
														//6, Short.MAX_VALUE)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(blockList, GroupLayout.PREFERRED_SIZE,
																		106, GroupLayout.PREFERRED_SIZE)
																.addComponent(btnPositionNewBlock))
														.addGroup(groupLayout.createSequentialGroup().addGroup(
																groupLayout.createParallelGroup(Alignment.LEADING)
																		.addComponent(
																				btnRemoveBlock)
																		.addComponent(btnMoveBlock))
																.addGap(24))
														.addGroup(groupLayout.createSequentialGroup()
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblHorizontal)
																		.addGroup(groupLayout.createSequentialGroup()
																				.addGap(6).addComponent(lblVertical)))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addGroup(groupLayout
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(txtHorizontal,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(txtVertical,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
																.addGap(11))))))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(backButton)
										.addGap(257).addComponent(errorMessage)))
						.addContainerGap(14, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING).addComponent(backButton).addComponent(errorMessage))
				.addGap(36)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(levelLabel)
						.addComponent(blockConfigurationLbl).addComponent(lblBlocksAvailable))
				.addGap(18)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(26)
										.addComponent(levelList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										)
								//.addComponent(blockAreaList)
								.addComponent(playAreaVisualizer)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(blockList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(41)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtHorizontal, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblHorizontal))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtVertical, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblVertical))
										.addGap(32).addComponent(btnPositionNewBlock).addGap(18)
										.addComponent(btnMoveBlock).addGap(79).addComponent(btnRemoveBlock)))
				.addGap(85)));
		
		editLevelWindow.getContentPane().setLayout(groupLayout);

		pack();

	}

	private void refreshData() {
		

		//errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			Integer index = 0;

			// levels
			levels = new HashMap<Integer, Integer>();
			levelList.removeAllItems();
			levelList.addItem("No Level selected");
			int nrLevels;
			try {
				nrLevels = Block223Controller.getCurrentDesignableGame().getNrLevels();
				for (int i = 0; i < nrLevels; i++) {
					levels.put(i, i + 1);
					levelList.addItem("Level " + (i + 1));
				}
			} catch (InvalidInputException e) {
				error = e.getMessage();
				e.printStackTrace();
			}

			// blocks available , game Blocks
			blocksAvailable = new HashMap<Integer, TOBlock>();
			blockList.removeAllItems();
			blockList.addItem("No block selected");
			index = 0;
			try {
				for (TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
					blocksAvailable.put(index, block);
					blockList.addItem(
							"#" + block.getId() + ", Points: " + block.getPoints() 
							+ ", R: " + block.getRed()
							+ ", G: " + block.getGreen()
							+ ", B: " + block.getBlue());
					index++;
				}
			} catch (InvalidInputException e) {
				error = e.getMessage();
				e.printStackTrace();
			}
			;
		}
	}

	private void refreshPlayAreaVisualizer(int level) {
		txtHorizontal.setText("");
		txtVertical.setText("");
		playAreaVisualizer.setLevel(levelList.getSelectedIndex());
		
		
//		int index = 0;
//		blocksInArea = new HashMap<Integer, TOGridCell>();
//		blockAreaList.removeAllItems();
//		blockAreaList.addItem("No cell selected");
//		if (level != 0 || level != -1) {
//			index = 0;
//			try {
//				for (TOGridCell gridCell : Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level)) {
//					blocksInArea.put(index, gridCell);
//					blockAreaList.addItem("Horizontal: " + gridCell.getGridHorizontalPosition() + ". Vertical: "
//							+ gridCell.getGridVerticalPosition() + ". Block id #" + gridCell.getId());
//					index++;
//				}
//			} catch (InvalidInputException e) {
//				// TODO Auto-generated catch block
//				error = e.getMessage();
//				e.printStackTrace();
//			}
//		}

		// blockAreaList.setSelectedIndex(-1);
	}

	// position block button
	private void positionBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";

		int selectedLevel = levelList.getSelectedIndex();
		if (selectedLevel <= 0)
			error = "Level needs to be selected !";

		TOBlock selectedBlock = blocksAvailable.get(blockList.getSelectedIndex() - 1);
		if (blockList.getSelectedIndex() < 0)
			error = error + " Block needs to be selected !";

		int x = 0;
		try {
			x = Integer.parseInt(txtHorizontal.getText());
		} catch (NumberFormatException e) {
			error = "Horizontal position needs to be a numerical value! ";
			JOptionPane.showMessageDialog(null, error);
		}

		int y = 0;
		try {
			y = Integer.parseInt(txtVertical.getText());
		} catch (NumberFormatException e) {
			error = "Vertical position needs to be a numerical value! ";
			JOptionPane.showMessageDialog(null, error);
		}

		try {
			Block223Controller.positionBlock(selectedBlock.getId(), selectedLevel, x, y);
		} catch (InvalidInputException e) {
			error = e.getMessage();
			JOptionPane.showMessageDialog(null, error);
			e.printStackTrace();

		}
		//refreshData();
		refreshPlayAreaVisualizer(selectedLevel);
		levelList.setSelectedIndex(selectedLevel);
	}

	// move block button
	private void moveBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";

		int selectedLevel = levelList.getSelectedIndex();
		if (selectedLevel <= 0)
			error = "Level needs to be selected !";

		TOGridCell selectedGridCell = playAreaVisualizer.getSelectedGridCell();

		int x = 0;
		try {
			x = Integer.parseInt(txtHorizontal.getText());
		} catch (NumberFormatException e) {
			error = "Horizontal position needs to be a numerical value! ";
			JOptionPane.showMessageDialog(null, error);
		}

		int y = 0;
		try {
			y = Integer.parseInt(txtVertical.getText());
		} catch (NumberFormatException e) {
			error = "Vertical position needs to be a numerical value! ";
			JOptionPane.showMessageDialog(null, error);
		}

		try {
			Block223Controller.moveBlock(selectedLevel, selectedGridCell.getGridHorizontalPosition(),
					selectedGridCell.getGridVerticalPosition(), x, y);
		} catch (InvalidInputException e) {
			error = e.getMessage();
			JOptionPane.showMessageDialog(null, error);
			e.printStackTrace();
		}
		//refreshData();
		refreshPlayAreaVisualizer(selectedLevel);
		levelList.setSelectedIndex(selectedLevel);

	}

	// remove block button
	private void removeBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedLevel = levelList.getSelectedIndex();
		if (selectedLevel <= 0)
			error = "Level needs to be selected !";

		TOGridCell selectedGridCell = playAreaVisualizer.getSelectedGridCell();
		if (blockList.getSelectedIndex() < 0)
			error = error + " Grid Cell needs to be selected !";

		try {
			Block223Controller.removeBlock(selectedLevel, selectedGridCell.getGridHorizontalPosition(),
					selectedGridCell.getGridVerticalPosition());
		} catch (InvalidInputException e) {
			error = e.getMessage();
			JOptionPane.showMessageDialog(null, error);
			e.printStackTrace();
		}
	//	refreshData();
		refreshPlayAreaVisualizer(selectedLevel);
		levelList.setSelectedIndex(selectedLevel);
		

	}

}
