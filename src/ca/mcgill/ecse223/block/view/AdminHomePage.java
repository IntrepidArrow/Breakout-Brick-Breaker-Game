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

public class AdminHomePage extends JFrame {

	private String userName;

	private JPanel contentPane;

	private JLabel userLabel;
	private JButton logoutButton;

	private JLabel titleLabel;
	private JList gameList;
	private JButton createButton;
	private JButton deleteButton;

	public AdminHomePage(String userName) {
		this.userName = userName;
		initComponents();
		refreshData();
	}

	public void initComponents() {

		setTitle("Admin Home Page");
		setBounds(100, 100, 1003, 676);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setFont(new Font("Arial", Font.PLAIN, 21));
		setContentPane(contentPane);

		userLabel = new JLabel("Hi, " + userName);
		userLabel.setBounds(746, 10, 83, 40);
		contentPane.add(userLabel);

		logoutButton = new JButton("Logout");
		logoutButton.setBounds(839, 10, 140, 40);
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
		titleLabel.setBounds(351, 10, 210, 100);
		contentPane.add(titleLabel);

		gameList = new JList();
		gameList.setBounds(145, 108, 600, 500);
		gameList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					try {
						Block223Controller.selectGame((String) gameList.getSelectedValue());
						setVisible(false);
						new GameSettingsPage().setVisible(true);
					} catch (InvalidInputException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});
		contentPane.add(gameList);

		createButton = new JButton("Create");
		createButton.setBounds(755, 108, 140, 40);
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
		deleteButton.setBounds(755, 159, 140, 40);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) gameList.getSelectedValue();
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

		gameList.setListData(gameNames.toArray());
	}
}
