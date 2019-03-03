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
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditBlocksPage {

	private JFrame frmGameBlockSettings;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGameBlockSettings = new JFrame();
		frmGameBlockSettings.setTitle("Game Block Settings");
		frmGameBlockSettings.setBounds(100, 100, 476, 361);
		frmGameBlockSettings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnBack = new JButton("Back");
		
		JButton btnLogout = new JButton("LogOut");
		
		JButton btnCreateNewBlock = new JButton("Create New Block");
		btnCreateNewBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO: Implement action to add new block to the game
			}
		});
		
		JButton btnUpdateBlock = new JButton("Update Block");
		btnUpdateBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Implement action to update a selected block in the game
			}
		});
		
		JButton btnDeleteBlock = new JButton("Delete Block");
		btnDeleteBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Implement action to delete a selected block in the game 
			}
		});
		
		JFormattedTextField frmtdtxtfldBlockSettings = new JFormattedTextField();
		frmtdtxtfldBlockSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmtdtxtfldBlockSettings.setEditable(false);
		frmtdtxtfldBlockSettings.setText("Block Settings:");
		
		JSeparator separator = new JSeparator();
		
		JFormattedTextField frmtdtxtfldGameBlockList = new JFormattedTextField();
		frmtdtxtfldGameBlockList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmtdtxtfldGameBlockList.setEditable(false);
		frmtdtxtfldGameBlockList.setText("Game Block List:");
		
		JFormattedTextField frmtdtxtfldpleaseSelectA = new JFormattedTextField();
		frmtdtxtfldpleaseSelectA.setEditable(false);
		frmtdtxtfldpleaseSelectA.setBackground(SystemColor.activeCaptionBorder);
		frmtdtxtfldpleaseSelectA.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtfldpleaseSelectA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmtdtxtfldpleaseSelectA.setText("* Please select a block to update or delete from the game");
		
		JComboBox comboBox = new JComboBox();
		GroupLayout groupLayout = new GroupLayout(frmGameBlockSettings.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnBack)
									.addPreferredGap(ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
									.addComponent(btnLogout))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnUpdateBlock, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
										.addComponent(btnCreateNewBlock, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
										.addComponent(btnDeleteBlock, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(frmtdtxtfldGameBlockList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(frmtdtxtfldpleaseSelectA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(17, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
							.addGap(94))))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(167)
					.addComponent(frmtdtxtfldBlockSettings, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(178, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack)
						.addComponent(btnLogout))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(frmtdtxtfldGameBlockList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdtxtfldpleaseSelectA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(frmtdtxtfldBlockSettings, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCreateNewBlock)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUpdateBlock)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDeleteBlock)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		frmGameBlockSettings.getContentPane().setLayout(groupLayout);
	}
}
