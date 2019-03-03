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
import javax.swing.JLabel;

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
				frmGameBlockSettings.setVisible(false);
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
		
		JLabel frmtdtxtfldBlockSettings = new JLabel();
		frmtdtxtfldBlockSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmtdtxtfldBlockSettings.setText("Add a new block to the game:");
		
		JSeparator separator = new JSeparator();
		
		JLabel frmtdtxtfldGameBlockList = new JLabel();
		frmtdtxtfldGameBlockList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmtdtxtfldGameBlockList.setText("Game Block List:");
		
		JLabel frmtdtxtfldpleaseSelectA = new JLabel();
		frmtdtxtfldpleaseSelectA.setBackground(SystemColor.activeCaptionBorder);
		frmtdtxtfldpleaseSelectA.setHorizontalAlignment(SwingConstants.CENTER);
		frmtdtxtfldpleaseSelectA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmtdtxtfldpleaseSelectA.setText("* Please select a block to update or delete from the game");
		
		JComboBox comboBox = new JComboBox();
		GroupLayout groupLayout = new GroupLayout(frmGameBlockSettings.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnBack)
									.addPreferredGap(ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
									.addComponent(btnLogout))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
									.addGap(78)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(28, Short.MAX_VALUE)
							.addComponent(frmtdtxtfldGameBlockList)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(frmtdtxtfldpleaseSelectA))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(frmtdtxtfldBlockSettings)
								.addComponent(btnCreateNewBlock, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDeleteBlock, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
						.addComponent(btnUpdateBlock, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
					.addGap(25))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack)
						.addComponent(btnLogout))
					.addGap(16)
					.addComponent(frmtdtxtfldBlockSettings)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCreateNewBlock)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(frmtdtxtfldGameBlockList)
						.addComponent(frmtdtxtfldpleaseSelectA))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUpdateBlock)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDeleteBlock)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		frmGameBlockSettings.getContentPane().setLayout(groupLayout);
	}
}
