package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.Block;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

public class AddBlockPage {

	private JFrame addBlockWindow;
	private JTextField redValue;
	private JTextField greenValue;
	private JTextField blueValue;
	private JTextField pointsValue;

	//Local variables for use
	private JLabel errorMessage = null;

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
		addBlockWindow.setBounds(100, 100, 377, 360);
		addBlockWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addBlockWindow.setVisible(true);

		//Code to open window in center of the screen, despite dimensions of the monitor application is run on
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		addBlockWindow.setLocation(dim.width/2-addBlockWindow.getSize().width/2, dim.height/2-this.addBlockWindow.getHeight()/2);
		
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//empty error message
				errorMessage.setText("");

				//converting entered data
				int redVal = 0;
				int greenVal = 0;
				int blueVal = 0;
				int pointsVal = 0;
				try {
					redVal = Integer.parseInt(redValue.getText());
					greenVal = Integer.parseInt(greenValue.getText());
					blueVal = Integer.parseInt(blueValue.getText());
					pointsVal = Integer.parseInt(pointsValue.getText());
					Block223Controller.addBlock(redVal, greenVal, blueVal, pointsVal);
					addBlockWindow.dispose();
					new EditBlocksPage();

				} catch (NumberFormatException e) {
					errorMessage.setText("All fields must have numerical values");
				} catch (InvalidInputException e) {
					errorMessage.setText(e.getMessage());
				}
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Canceling should take back to the previous window : Block Setting page 
				addBlockWindow.dispose();
				new EditBlocksPage();
			}
		});

		errorMessage = new JLabel("");
		errorMessage.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(addBlockWindow.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(128, Short.MAX_VALUE)
						.addComponent(lblDefineBlockValues)
						.addGap(144))
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblDefineBlockPoints)
						.addContainerGap(265, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap(120, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(frmtdtxtfldGreen)
												.addComponent(frmtdtxtfldBlue)
												.addComponent(frmtdtxtfldRed))
										.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(110)
										.addComponent(lblPoints)
										.addGap(23)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(greenValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(redValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(blueValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(pointsValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(103))
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(67)
						.addComponent(btnNewButton)
						.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
						.addComponent(btnCancel)
						.addGap(66))
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(frmtdtxtfldDefineBlockColor)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
						.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(20)
						.addComponent(lblDefineBlockValues)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(18)
										.addComponent(frmtdtxtfldDefineBlockColor))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(25)
										.addComponent(errorMessage)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(redValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtdtxtfldRed))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldGreen)
								.addComponent(greenValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(frmtdtxtfldBlue)
								.addComponent(blueValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(30)
						.addComponent(lblDefineBlockPoints)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPoints)
								.addComponent(pointsValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(37)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton)
								.addComponent(btnCancel))
						.addContainerGap(32, Short.MAX_VALUE))
				);
		addBlockWindow.getContentPane().setLayout(groupLayout);
	}
}
