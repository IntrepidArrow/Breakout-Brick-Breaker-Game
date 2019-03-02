package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignupPage extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JLabel lblAdminPassword;
	private JLabel lblPlayerPassword;
	private JLabel lblUsername;
	private JPasswordField playerPasswordField;
	private JPasswordField adminPasswordField;
	private JLabel label;
	private JLabel lblOptional;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignupPage frame = new SignupPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignupPage() {
		setTitle("Sign Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 353, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Block223Controller.register(
							usernameField.getText(),
							Block223Controller.getSHA512(new String(playerPasswordField.getPassword()), "223"),
							Block223Controller.getSHA512(new String(adminPasswordField.getPassword()), "223")
							);
					setVisible(false);
					Block223Application.getStartInstance().setVisible(true);
				} catch (InvalidInputException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.toString());
				}
				
			}
		});
		button.setBounds(42, 231, 225, 25);
		contentPane.add(button);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(151, 32, 116, 22);
		contentPane.add(usernameField);
		//more responsive tooltips
		ToolTipManager.sharedInstance().setInitialDelay(0);
		ToolTipManager.sharedInstance().setDismissDelay(6000);
		ToolTipManager.sharedInstance().setReshowDelay(500);
		
		lblAdminPassword = new JLabel("Admin Password:");
		lblAdminPassword.setToolTipText("");
		
		lblAdminPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdminPassword.setBounds(12, 135, 116, 16);
		contentPane.add(lblAdminPassword);
		
		lblPlayerPassword = new JLabel("Player Password:");
		lblPlayerPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayerPassword.setBounds(12, 85, 116, 16);
		contentPane.add(lblPlayerPassword);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(12, 35, 116, 16);
		contentPane.add(lblUsername);
		
		playerPasswordField = new JPasswordField();
		playerPasswordField.setBounds(151, 82, 116, 22);
		contentPane.add(playerPasswordField);
		
		adminPasswordField = new JPasswordField();
		adminPasswordField.setBounds(151, 132, 116, 22);
		contentPane.add(adminPasswordField);
		
		label = new JLabel("?");
		label.setToolTipText("If you want to design games you will need to register an admin password");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setBounds(271, 135, 18, 16);
		contentPane.add(label);
		
		lblOptional = new JLabel("optional");
		lblOptional.setToolTipText("");
		lblOptional.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOptional.setBounds(151, 117, 116, 16);
		contentPane.add(lblOptional);
	}
}
