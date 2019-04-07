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
import ca.mcgill.ecse223.block.controller.TOPlayableGame;
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
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class PlayerHomePage extends JFrame {

	private String userName;
	private String gameNameHolder; 
	private JPanel contentPane;

	private JLabel userLabel;
	private JButton logoutButton;

	private JLabel titleLabel;
	private JList currentGamesList;
	private JButton playButton;
	private JButton HofButton;
	private JList playableGamesList;
	//private JButton btnTestGame;

	public PlayerHomePage(String userName) {
		this.userName = userName;
		initComponents();
		refreshData();
	}

	public void initComponents() {

		setTitle("Player Home Page");
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

		currentGamesList = new JList(); // replaces playedGames list 
		currentGamesList.setBounds(37, 148, 600, 207);
		gameNameHolder = (String) currentGamesList.getSelectedValue();
		contentPane.add(currentGamesList);

		playButton = new JButton("Play");
		playButton.setBounds(672, 148, 140, 40);
		playButton.addActionListener(new ActionListener() { //change the content of this method in order for it to redirect you on another page (main UI)
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(currentGamesList.getSelectedValue()==null) ) {
					System.out.println(currentGamesList.getSelectedValue());
					//TODO: Close Window, and create play game screen (new Play Mode) 
					
				}
			}
		});
		contentPane.add(playButton);

		HofButton = new JButton("View Hall Of Fame");
		HofButton.setBounds(672, 218, 140, 40);
		HofButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //change the content of this method in order to show the HOF.
				
				//TODO: Initialize Pop up window for Hall of Fame. 
				//new HallOfFamePage(gameNameHolder).setVisible(true); // will be resolved when the view of the hof will be added to the project. 
				
			}
		});
		contentPane.add(HofButton);

	playableGamesList = new JList(); // replaces published game list. 
		playableGamesList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(playableGamesList != null) {
					if(evt.getClickCount() == 2 && playableGamesList.getSelectedValue() != null) {
						try {
							Block223Controller.selectGame((String) playableGamesList.getSelectedValue());
						} catch (InvalidInputException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				}
			}
		}); 
		playableGamesList.setBounds(37, 404, 600, 207);
		contentPane.add(playableGamesList);

		JLabel lblDesignableGames = new JLabel("Played Games (resumable) :");
		lblDesignableGames.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDesignableGames.setBounds(37, 113, 322, 24);
		contentPane.add(lblDesignableGames);

		JLabel publishedGamesList = new JLabel("Playable Games :");
		publishedGamesList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		publishedGamesList.setBounds(37, 369, 166, 24);
		contentPane.add(publishedGamesList);

		JLabel errorMessage = new JLabel("");
		errorMessage.setForeground(Color.RED);
		errorMessage.setBounds(213, 122, 599, 14);
		contentPane.add(errorMessage);
		
		//btnTestGame = new JButton("Test Game");
		//btnTestGame.setBounds(672, 353, 140, 40);
		//contentPane.add(btnTestGame);

	}

	public void refreshData() {

		// refresh game list
		List<String> gameNames = new ArrayList<>();
		List<TOPlayableGame> playedGames = null;
		
		List<String> publishedGameNames = new ArrayList<>();
		List<TOPlayableGame> playableGames1 = null;

		try {
			playedGames = Block223Controller.getPlayedGames();
			playableGames1 = Block223Controller.getPossiblePlayableGames();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (TOPlayableGame game : playedGames)
			gameNames.add(game.getName());

		for (TOPlayableGame game2 : playableGames1) {
			publishedGameNames.add(game2.getName());
			playedGames.remove(game2);
		}
		
		currentGamesList.setListData(gameNames.toArray());
		playableGamesList.setListData(publishedGameNames.toArray());
	}
}
