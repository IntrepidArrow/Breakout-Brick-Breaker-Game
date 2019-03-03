package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AddBlockPage {

	private JFrame addBlockWindow;
	private JTextField redValue;
	private JTextField greenValue;
	private JTextField blueValue;
	private JTextField pointsValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBlockPage window = new AddBlockPage();
					window.addBlockWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddBlockPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addBlockWindow = new JFrame();
		addBlockWindow.setBounds(100, 100, 452, 423);
		addBlockWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel frmtdtxtfldDefineBlockColor = new JLabel();
		frmtdtxtfldDefineBlockColor.setText("Block Color Values:");
		
		JLabel frmtdtxtfldRed = new JLabel();
		frmtdtxtfldRed.setText("Red : ");
		
		JLabel frmtdtxtfldGreen = new JLabel();
		frmtdtxtfldGreen.setText("Green : ");
		
		JLabel frmtdtxtfldBlue = new JLabel();
		frmtdtxtfldBlue.setText("Blue : ");
		
		redValue = new JTextField();
		frmtdtxtfldRed.setLabelFor(redValue);
		redValue.setColumns(10);
		
		greenValue = new JTextField();
		frmtdtxtfldGreen.setLabelFor(greenValue);
		greenValue.setColumns(10);
		
		blueValue = new JTextField();
		frmtdtxtfldBlue.setLabelFor(blueValue);
		blueValue.setColumns(10);
		
		JLabel lblDefineBlockValues = new JLabel("Define Block Values");
		
		JLabel lblDefineBlockPoints = new JLabel("Block Points Value:");
		
		JLabel lblPoints = new JLabel("Points :");
		
		pointsValue = new JTextField();
		lblPoints.setLabelFor(pointsValue);
		pointsValue.setColumns(10);
		
		JButton btnNewButton = new JButton("Create Block");
		
		JButton btnCancel = new JButton("Cancel");
		GroupLayout groupLayout = new GroupLayout(addBlockWindow.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(154, Short.MAX_VALUE)
					.addComponent(lblDefineBlockValues)
					.addGap(144))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(frmtdtxtfldDefineBlockColor)
					.addContainerGap(234, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDefineBlockPoints)
					.addContainerGap(352, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(114, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(frmtdtxtfldRed)
								.addComponent(frmtdtxtfldGreen)
								.addComponent(frmtdtxtfldBlue))
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(110)
							.addComponent(lblPoints)
							.addGap(23)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(pointsValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(greenValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(redValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(blueValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(103))))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(73)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addGap(70))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblDefineBlockValues)
					.addGap(18)
					.addComponent(frmtdtxtfldDefineBlockColor)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(frmtdtxtfldRed)
						.addComponent(redValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(frmtdtxtfldGreen)
						.addComponent(greenValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(frmtdtxtfldBlue)
						.addComponent(blueValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDefineBlockPoints)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblPoints))
						.addComponent(pointsValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnCancel))
					.addGap(29))
		);
		addBlockWindow.getContentPane().setLayout(groupLayout);
	}
}
