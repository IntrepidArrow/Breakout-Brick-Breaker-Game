package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class AdminHomePage extends JFrame {

	private String userName;

	private JPanel contentPane;

	private JLabel userLabel;
	private JButton logoutButton;

	private JLabel titleLabel;
	private JList designableGamesList;
	private JButton createButton;
	private JButton deleteButton;
	private JList publishedGameList;
	private JButton btnTestGame;

	public AdminHomePage(String userName) {
		this.userName = userName;
		initComponents();
		refreshData();
	}

	public void initComponents() {

		setTitle("Admin Home Page");
		setBounds(100, 100, 851, 676);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setFont(new Font("Arial", Font.PLAIN, 21));
		setContentPane(contentPane);

		userLabel = new JLabel("Hi, " + userName);
		userLabel.setBounds(579, 22, 83, 40);
		contentPane.add(userLabel);

		logoutButton = new JButton("Logout");
		logoutButton.setBounds(672, 22, 140, 40);
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Block223Controller.logout();
				setVisible(false);
				new StartPage().setVisible(true);
			}
		});
		contentPane.add(logoutButton);

		titleLabel = new JLabel("My Games");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		titleLabel.setBounds(268, 11, 210, 100);
		contentPane.add(titleLabel);

		designableGamesList = new JList();
		designableGamesList.setBounds(37, 148, 600, 207);
		designableGamesList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					try {
						Block223Controller.selectGame((String) designableGamesList.getSelectedValue());
						setVisible(false);
						new GameSettingsPage().setVisible(true);
					} catch (InvalidInputException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
				if(evt.getClickCount() == 1) {
					try {
						Block223Controller.selectGame((String) designableGamesList.getSelectedValue());
					} catch (InvalidInputException m) {
						JOptionPane.showMessageDialog(null, m.getMessage());
					}
				}
			}
		});
		contentPane.add(designableGamesList);

		createButton = new JButton("Create");
		createButton.setBounds(672, 251, 140, 40);
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "Game name:", "Create Game", JOptionPane.PLAIN_MESSAGE);

				if (name != null) {
					try {
						Block223Controller.createGame(name);
						refreshData();
					} catch (InvalidInputException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});
		contentPane.add(createButton);

		deleteButton = new JButton("Delete");
		deleteButton.setBounds(672, 302, 140, 40);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String name = (String) designableGamesList.getSelectedValue();
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete game " + name,
						"Delete Game", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if (option == JOptionPane.OK_OPTION) {
					try {
						Block223Controller.deleteGame(name);
						refreshData();
					} catch (InvalidInputException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});
		contentPane.add(deleteButton);

		publishedGameList = new JList();
		publishedGameList.setBounds(37, 404, 600, 207);
		contentPane.add(publishedGameList);

		JLabel lblDesignableGames = new JLabel("Designable Games :");
		lblDesignableGames.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDesignableGames.setBounds(37, 113, 166, 24);
		contentPane.add(lblDesignableGames);

		JLabel publishedGamesList = new JLabel("Published Games :");
		publishedGamesList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		publishedGamesList.setBounds(37, 369, 166, 24);
		contentPane.add(publishedGamesList);

		JButton btnNewButton = new JButton("Publish Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					String game = (String) designableGamesList.getSelectedValue();
					if (game != null) {
						int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to Publish game " + game,
								"Publish Game", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						if(option == JOptionPane.OK_OPTION) {
							Block223Controller.publishGame();
							//JOptionPane.showMessageDialog(null, "Game succesfully published");	
						} 
						if (option == JOptionPane.OK_CANCEL_OPTION) {
							refreshData();
						}
					}
				} catch (InvalidInputException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

			}
		});
		btnNewButton.setBounds(672, 404, 140, 40);
		contentPane.add(btnNewButton);

		JLabel errorMessage = new JLabel("");
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(213, 122, 599, 14);
		contentPane.add(errorMessage);
		
		btnTestGame = new JButton("Test Game");
		btnTestGame.setBounds(672, 353, 140, 40);
		contentPane.add(btnTestGame);

	}

	public void refreshData() {

		// refresh game list
		List<String> gameNames = new ArrayList<>();
		List<TOGame> designableGames = null;

		try {
			designableGames = Block223Controller.getDesignableGames();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (TOGame game : designableGames)
			gameNames.add(game.getName());

		designableGamesList.setListData(gameNames.toArray());
	}
}
