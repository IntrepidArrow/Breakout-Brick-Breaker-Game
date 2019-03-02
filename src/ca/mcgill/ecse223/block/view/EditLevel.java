package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGridCell;

public class EditLevel extends JFrame {

	private static final long serialVersionUID = -4426310869376315542L;

	// UI elements
	public JFrame frame;
	private JLabel errorMessage;
	private JButton backButton;
	private JButton logoutButton;
	private JLabel usernameLabel;

	// Levels
	private JLabel levelLabel;
	private JComboBox<String> levelList; // maybe change to a table later
	private JButton addLevelBtn;
	private JButton removeLevelBtn;

	//block play area
	private JLabel blockConfigurationLbl;
	private JPanel areaPanel; 
	private PlayAreaVisualizer playAreaVisualizer;

	//Blocks Available
	private JLabel lblBlocksAvailable;
	private JComboBox<String> blockList; // maybe change to a table later
	private JButton btnPositionNewBlock;
	private JButton btnMoveBlock;
	private JButton btnRemoveBlock;
	private JLabel lblHorizontal;
	private JTextField txtHorizontal;
	private JLabel lblVertical;
	private JTextField txtVertical;

	//Data elements
	private String error = null;
	//levels
	private HashMap<Integer, Integer> levels;
	//blocks
	private HashMap<Integer, TOBlock> blocksAvailable;
	//play area
	private HashMap<Integer, TOGridCell> blocksInArea;

	/** Creates new form BtmsPage */
	public EditLevel() {
		initComponents();
		refreshData();
		//refreshPlayAreaVisualizer();
	}


	private void initComponents() {


		frame = new JFrame();
		frame.setBounds(100, 100, 841, 629);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// elements for frame
		backButton = new JButton("Back");
		errorMessage = new JLabel("error");
		errorMessage.setForeground(Color.RED);

		// elements for levels			
		levelLabel = new JLabel("Levels");
		levelList = new JComboBox();
		levelList.setMaximumRowCount(99);
		levelList.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				refreshPlayAreaVisualizer();
			}
		});
		addLevelBtn = new JButton("Add Level");
		addLevelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addLevelButtonActionPerformed(evt);
			}
		});

		removeLevelBtn = new JButton("Remove Level ");
		removeLevelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeLevelButtonActionPerformed(evt);
			}
		});

		// elements for play area
		blockConfigurationLbl = new JLabel("Block configuration");

		playAreaVisualizer = new PlayAreaVisualizer();
		playAreaVisualizer.setMinimumSize(new Dimension(390, 390));


		JPanel areaPanel = new JPanel();
		areaPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		areaPanel.setBackground(Color.LIGHT_GRAY);

		// elements for blocks available
		lblBlocksAvailable = new JLabel("Blocks Available");

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
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(12)
														.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
																.addComponent(addLevelBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(removeLevelBtn, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 115, Short.MAX_VALUE)))
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(39)
														.addComponent(levelLabel))
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(22)
														.addComponent(levelList, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(194)
														.addComponent(blockConfigurationLbl)
														.addGap(263)
														.addComponent(lblBlocksAvailable))
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(53)
														.addComponent(areaPanel, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
														.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																		.addComponent(blockList, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
																		.addComponent(btnPositionNewBlock))
																.addGroup(groupLayout.createSequentialGroup()
																		.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																				.addComponent(btnRemoveBlock)
																				.addComponent(btnMoveBlock))
																		.addGap(24))
																.addGroup(groupLayout.createSequentialGroup()
																		.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																				.addComponent(lblHorizontal)
																				.addGroup(groupLayout.createSequentialGroup()
																						.addGap(6)
																						.addComponent(lblVertical)))
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																				.addComponent(txtHorizontal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addComponent(txtVertical, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																		.addGap(11))))))
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(backButton)
										.addGap(257)
										.addComponent(errorMessage)))
						.addContainerGap(14, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(backButton)
								.addComponent(errorMessage))
						.addGap(36)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(levelLabel)
								.addComponent(blockConfigurationLbl)
								.addComponent(lblBlocksAvailable))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(26)
										.addComponent(levelList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(245)
										.addComponent(addLevelBtn)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(removeLevelBtn))
								.addComponent(areaPanel, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(blockList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(41)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtHorizontal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblHorizontal))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtVertical, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblVertical))
										.addGap(32)
										.addComponent(btnPositionNewBlock)
										.addGap(18)
										.addComponent(btnMoveBlock)
										.addGap(79)
										.addComponent(btnRemoveBlock)))
						.addGap(85))
				);
		GroupLayout gl_panel = new GroupLayout(areaPanel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 339, Short.MAX_VALUE)
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 375, Short.MAX_VALUE)
				);
		areaPanel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);


		pack();

	}

	private void refreshData() {

		Integer index = 0;

		// levels
		levels = new HashMap<Integer, Integer>();
		levelList.removeAllItems();
		levelList.addItem("No Level selected");
		index = 0;
		int nrLevels;
		try {
			nrLevels = Block223Controller.getCurrentDesignableGame().getNrLevels();
			for (int i = 0 ; i < nrLevels  ; i++) {
				levels.put(i,i+1 );
				levelList.addItem("Level " + (i + 1));
				index++;
			};
			levelList.setSelectedIndex(-1);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
			e.printStackTrace();
		}

		// blocks available
		blocksAvailable = new HashMap<Integer,TOBlock>();
		blockList.removeAllItems();
		blockList.addItem("No block selected");
		index = 0;
		try {
			for (TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
				blocksAvailable.put(index, block);
				blockList.addItem("#" + block.getId() + ", Points: " + block.getPoints() + ", Color: " + block.getRed());
				index++;
			}
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
			e.printStackTrace();
		};
		blockList.setSelectedIndex(-1);
	}

	private void refreshPlayAreaVisualizer() {
		playAreaVisualizer.setLevel(levelList.getSelectedIndex());
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


