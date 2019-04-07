package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOUserMode;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.UserRole;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 316, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Code to open window in center of the screen, despite dimensions of the monitor application is run on
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(52, 211, 225, 25);
		contentPane.add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String userName = usernameField.getText();
					Block223Controller.login(userName,
							Block223Controller.getSHA512(new String(passwordField.getPassword()), "223"));

					setVisible(false);

					TOUserMode mode = Block223Controller.getUserMode();

					if (mode.getMode().equals(TOUserMode.Mode.Design))
						new AdminHomePage(userName).setVisible(true);

				} catch (InvalidInputException e) {
					JOptionPane.showMessageDialog(null, e.toString());
					usernameField.setText("");
					passwordField.setText("");
					
				}

			}
		});

		usernameField = new JTextField();
		usernameField.setBounds(161, 76, 116, 22);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(52, 79, 63, 16);
		contentPane.add(lblNewLabel);

		JLabel lblPlayerPassword = new JLabel("Password:");
		lblPlayerPassword.setBounds(52, 129, 63, 16);
		contentPane.add(lblPlayerPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(161, 126, 116, 22);
		contentPane.add(passwordField);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				StartPage.getStartInstance().setVisible(true);
			}
		});
		btnBack.setBounds(0, 0, 71, 25);
		contentPane.add(btnBack);
	}

}
