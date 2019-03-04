package ca.mcgill.ecse223.block.view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class AlbertView {

	private JFrame frame;
	private JTextField txtHorizontal;
	private JTextField txtVertical;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlbertView window = new AlbertView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AlbertView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 841, 629);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel levelLabel = new JLabel("Levels");
		
		JButton backButton = new JButton("Back");
		
		JButton addLevelBtn = new JButton("Add Level");
		addLevelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton removeLevelBtn = new JButton("Remove Level ");
		removeLevelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel blockConfigurationLbl = new JLabel("Block configuration");
		
		JPanel areaPanel = new JPanel();
		areaPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		areaPanel.setBackground(Color.LIGHT_GRAY);
		
		JComboBox levelList = new JComboBox();
		levelList.setMaximumRowCount(99);
		
		JLabel lblBlocksAvailable = new JLabel("Blocks Available");
		
		JComboBox blockList = new JComboBox();
		
		JButton btnPositionNewBlock = new JButton("Position New Block");
		
		JButton btnMoveBlock = new JButton("Move Block");
		
		JButton btnRemoveBlock = new JButton("Remove Block");
		
		txtHorizontal = new JTextField();
		txtHorizontal.setToolTipText("Horizontal");
		txtHorizontal.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		txtHorizontal.setColumns(10);
		
		txtVertical = new JTextField();
		txtVertical.setToolTipText("Vertical");
		txtVertical.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		txtVertical.setColumns(10);
		
		JLabel lblHorizontal = new JLabel("Horizontal");
		
		JLabel lblVertical = new JLabel("Vertical");
		
		JLabel errorMessage = new JLabel("error");
		errorMessage.setForeground(Color.RED);
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
	}
}








//STEP 5


