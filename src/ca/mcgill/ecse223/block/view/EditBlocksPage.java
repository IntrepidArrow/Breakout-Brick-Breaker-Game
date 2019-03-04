package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;

import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class EditBlocksPage {

	private JFrame frmGameBlockSettings;
	private JTextField redValue;
	private JTextField greenValue;
	private JTextField blueValue;
	private JTextField pointsValue;

	private JComboBox<String> selectBlockComboBox; 
	private HashMap<Integer, TOBlock> gameBlocks;
	private JLabel errorMessageToUse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditBlocksPage window = new EditBlocksPage();
					window.frmGameBlockSettings.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditBlocksPage() {
		initialize();
		refreshBlockData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGameBlockSettings = new JFrame();
		frmGameBlockSettings.setTitle("Game Block Settings");
		frmGameBlockSettings.setBounds(100, 100, 572, 374);
		frmGameBlockSettings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGameBlockSettings.setVisible(true);

		JButton btnBack = new JButton("Back");	//take back to game settings page
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmGameBlockSettings.dispose();
				new GameSettingsPage();
			}
		});

		JButton btnLogout = new JButton("LogOut");	//Logout of the game 
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGameBlockSettings.dispose();
			}
		});

		JButton btnCreateNewBlock = new JButton("Create New Block");	//opens another window to add block characteristics
		btnCreateNewBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmGameBlockSettings.dispose();
				new AddBlockPage();
			}
		});

		JButton btnUpdateBlock = new JButton("Update Block");
		btnUpdateBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorMessageToUse.setText("");
				int selectedBlock = selectBlockComboBox.getSelectedIndex();

				if(selectedBlock < 1) {
					errorMessageToUse.setText("A block needs to be selected to be updated!");
					return;
				}

				//get block to update and display data on the screen
				TOBlock blockToUpdate = gameBlocks.get(selectedBlock-1);

				try {
					int newRedVal = Integer.parseInt(redValue.getText());
					int newGreenVal = Integer.parseInt(greenValue.getText());
					int newBlueVal = Integer.parseInt(greenValue.getText());
					int newPointsVal = Integer.parseInt(pointsValue.getText());

					//update block controller call
					Block223Controller.updateBlock(blockToUpdate.getId(), newRedVal, newGreenVal, newBlueVal, newPointsVal);
				} catch (InvalidInputException m) {
					errorMessageToUse.setText(m.getMessage());
				} catch (NumberFormatException m) {
					errorMessageToUse.setText(m.getMessage());
				}

			}
		});

		JButton btnDeleteBlock = new JButton("Delete Block");
		btnDeleteBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorMessageToUse.setText("");
				int selectedBlock = selectBlockComboBox.getSelectedIndex();

				if(selectedBlock < 1) {
					errorMessageToUse.setText("A block needs to be selected to be deleted!");
					return;
				}

				//calling controller methods to implement action
				try {
					Block223Controller.deleteBlock(gameBlocks.get(selectedBlock-1).getId());

					//refresh block list that will be displayed in combo box list
					refreshBlockData();
				} catch (InvalidInputException m) {
					errorMessageToUse.setText(m.getMessage());
				}
			}
		});

		JLabel frmtdtxtfldBlockSettings = new JLabel();
		frmtdtxtfldBlockSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmtdtxtfldBlockSettings.setText("Click to create a new block!");

		JSeparator separator = new JSeparator();

		JLabel frmtdtxtfldGameBlockList = new JLabel();
		frmtdtxtfldGameBlockList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmtdtxtfldGameBlockList.setText("Game Block List:");

		JLabel frmtdtxtfldpleaseSelectA = new JLabel();
		frmtdtxtfldpleaseSelectA.setBackground(SystemColor.activeCaptionBorder);
		frmtdtxtfldpleaseSelectA.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtfldpleaseSelectA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmtdtxtfldpleaseSelectA.setText("* Please select a block to update or delete from the game");

		selectBlockComboBox = new JComboBox<String>();
		selectBlockComboBox.addItem("");
		selectBlockComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorMessageToUse.setText("");
				//combo box operation 
				int selectedBlockIndex = selectBlockComboBox.getSelectedIndex();
				if(selectedBlockIndex >= 1) {
					TOBlock block = gameBlocks.get(selectedBlockIndex-1);
					redValue.setText(Integer.toString(block.getRed()));
					greenValue.setText(Integer.toString(block.getGreen()));
					blueValue.setText(Integer.toString(block.getBlue()));
					pointsValue.setText(Integer.toString(block.getPoints()));					
				} else {
					redValue.setText("");
					greenValue.setText("");
					blueValue.setText("");
					pointsValue.setText("");
				}
			}
		});

		JLabel lblRed = new JLabel("Red:");

		redValue = new JTextField();
		redValue.setColumns(10);

		JLabel lblGreen = new JLabel("Green:");

		greenValue = new JTextField();
		greenValue.setColumns(10);

		JLabel lblBlue = new JLabel("Blue:");

		blueValue = new JTextField();
		blueValue.setColumns(10);

		JLabel lblPoints = new JLabel("Points:");

		pointsValue = new JTextField();
		pointsValue.setColumns(10);

		errorMessageToUse = new JLabel("");
		errorMessageToUse.setForeground(Color.RED);

		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorMessageToUse.setText("");

				try {
					Block223Controller.saveGame();
				} catch (InvalidInputException m) {
					errorMessageToUse.setText("");
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmGameBlockSettings.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(selectBlockComboBox, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(frmtdtxtfldGameBlockList)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(frmtdtxtfldpleaseSelectA))
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(btnBack)
																.addGap(18)
																.addComponent(errorMessageToUse, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
																.addGap(18)
																.addComponent(btnLogout))
														.addComponent(btnCreateNewBlock, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE))
												.addComponent(frmtdtxtfldBlockSettings))
										.addGap(12))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addGroup(groupLayout.createSequentialGroup()
																		.addGroup(groupLayout.createSequentialGroup()
																				.addComponent(lblRed, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
																				.addGap(18)
																				.addComponent(redValue, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
																				.addGap(18)
																				.addComponent(lblGreen, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addGroup(groupLayout.createSequentialGroup()
																				.addGap(4)
																				.addComponent(greenValue, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
																				.addGap(18)
																				.addComponent(lblBlue, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(ComponentPlacement.UNRELATED)
																				.addComponent(blueValue, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
																				.addGap(18)
																				.addComponent(lblPoints, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addComponent(pointsValue, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
																.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																		.addComponent(btnDeleteBlock, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
																		.addComponent(btnUpdateBlock, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)))
														.addGap(2))
												.addComponent(btnSaveChanges))
										.addContainerGap())))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnBack)
								.addComponent(btnLogout)
								.addComponent(errorMessageToUse))
						.addGap(16)
						.addComponent(frmtdtxtfldBlockSettings)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnCreateNewBlock)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldGameBlockList)
								.addComponent(frmtdtxtfldpleaseSelectA))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(selectBlockComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRed)
								.addComponent(lblGreen)
								.addComponent(greenValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBlue)
								.addComponent(blueValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPoints)
								.addComponent(pointsValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(redValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(btnUpdateBlock)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDeleteBlock)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnSaveChanges)
						.addContainerGap(26, Short.MAX_VALUE))
				);
		frmGameBlockSettings.getContentPane().setLayout(groupLayout);
	}
	
	private void refreshBlockData() {
		Integer index = 0;
		redValue.setText("");
		greenValue.setText("");
		blueValue.setText("");
		pointsValue.setText("");
		gameBlocks = new HashMap<Integer, TOBlock>();
		selectBlockComboBox.removeAllItems();
		selectBlockComboBox.addItem("");
		index = 0;
		try {
			for (TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
				gameBlocks.put(index, block);
				selectBlockComboBox.addItem("R: "+block.getRed()+" G: "+block.getGreen()+" B: "+block.getBlue()+" Points: "+block.getPoints());
				index++;
			}
		} catch (InvalidInputException e) {
			errorMessageToUse.setText(e.getMessage());
		}
	}
}
