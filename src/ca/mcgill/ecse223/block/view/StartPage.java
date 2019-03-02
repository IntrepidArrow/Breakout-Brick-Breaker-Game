package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class StartPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartPage frame = new StartPage();
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
	public StartPage() {
		setTitle("Block223 Launcher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new LoginPage().setVisible(true);
			}
		});
		btnNewButton.setBounds(49, 179, 97, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LoginPage().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(278, 179, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblWelcomeToBlock = new JLabel("Welcome to Block223");
		lblWelcomeToBlock.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		lblWelcomeToBlock.setBounds(113, 57, 191, 53);
		contentPane.add(lblWelcomeToBlock);
		
		JLabel lblNewUser = new JLabel("New user?");
		lblNewUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewUser.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		lblNewUser.setBounds(278, 145, 97, 31);
		contentPane.add(lblNewUser);
	}
}
