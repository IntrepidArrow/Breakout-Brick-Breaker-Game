package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;
//import ca.mcgill.ecse223.block.model.Game;
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
import java.sql.Savepoint;
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
				publishedGameList.clearSelection();
				if (evt.getClickCount() == 2 && designableGamesList.getSelectedValue() != null) {
					try {

						Block223Controller.selectGame((String) designableGamesList.getSelectedValue());
						dispose();
						new GameSettingsPage().setVisible(true);
					} catch (InvalidInputException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
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

				String desGameSelect = (String) designableGamesList.getSelectedValue();
				String pubGameSelect = (String) publishedGameList.getSelectedValue();
				try {
//					if(designableGamesList != null && desGameSelect == null) {
//						Block223Controller.deleteGame(desGameSelect);
//					}
					if(desGameSelect != null) {
						int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete game " + desGameSelect,
								"Delete Game", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						if(option == JOptionPane.OK_OPTION) {
							Block223Controller.deleteGame(desGameSelect);
							refreshData();
						} else if (option == JOptionPane.OK_CANCEL_OPTION) {
							refreshData();
						}
					}
					
					if(pubGameSelect != null) {
						Block223Controller.deleteGame(pubGameSelect);
					}
				} catch (InvalidInputException ex ) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		contentPane.add(deleteButton);

		publishedGameList = new JList();
		publishedGameList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				designableGamesList.clearSelection();
					if(evt.getClickCount() == 2 && publishedGameList.getSelectedValue() != null) {
						
						try {
							Block223Controller.selectGame((String) publishedGameList.getSelectedValue());
						} catch (InvalidInputException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				}
//			}
		});
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
							Block223Controller.selectGame(game);
							Block223Controller.publishGame();
							refreshData();
							Block223Controller.saveGame();	//Should the Game be saved?	
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
		btnTestGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String desGame = (String) designableGamesList.getSelectedValue();
				String pubGame = (String) publishedGameList.getSelectedValue();

				if(desGame != null) {
					try {
						Block223Controller.selectGame(desGame);
						PlayModeView testGround = new PlayModeView(true);
					} catch (InvalidInputException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
				if(pubGame != null) {
					try {
						Block223Controller.selectGame(pubGame);
					} catch (InvalidInputException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
				
			}
		});
		btnTestGame.setBounds(672, 353, 140, 40);
		contentPane.add(btnTestGame);

	}

	public void refreshData() {

		// refresh game list
		List<String> gameNames = new ArrayList<>();
		List<TOGame> designableGames = null;

		List<String> publishedGameNames = new ArrayList<>();
		List<TOGame> publishedGames = null;

		try {
			designableGames = Block223Controller.getDesignableGames();
			publishedGames = Block223Controller.getPublishedGames();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (TOGame game : designableGames)
			gameNames.add(game.getName());

		for (TOGame game2 : publishedGames) {
			publishedGameNames.add(game2.getName());
			designableGames.remove(game2);
		}

		designableGamesList.setListData(gameNames.toArray());
		publishedGameList.setListData(publishedGameNames.toArray());
	}
}
