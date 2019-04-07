package ca.mcgill.ecse223.block.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOHallOfFame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;
import ca.mcgill.ecse223.block.model.Game;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class HallOfFame extends JFrame {
	private String gameName; 
	private static final long serialVersionUID = -4426310869335015542L;
	private JFrame frame; 
	private JScrollPane HofScrollPane; 
	private DefaultTableModel HofDtm; 
	private String HofColumnNames[] = {"Position", "Name", "Score"};
	private JTable HofTable;
	private JButton closeBtn;
	
	/**
	 * Launch the application.
	 */
//	 public static void main(String[] args) {
//		 
//		 Game yo = new Game();
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				
//				
//				try {
//					HallOfFame window = new HallOfFame("yo");
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public HallOfFame(String gameName) {
		this.gameName = gameName; 
		setComponent();
	}
	
	private String getGameName(){
		return this.gameName;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void setComponent() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		HofTable = new JTable();
		
		
		
	//	private final long serialVersionUID = 1L; // see what the problem is  later 
		
		closeBtn = new JButton("Close");
		HofScrollPane = new JScrollPane(HofTable);
		frame.add(HofScrollPane); 
		Dimension d = HofTable.getPreferredSize(); 
		HofScrollPane.setPreferredSize(new Dimension(d.width, HofTable.getHeight()));
		HofScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(41)
							.addComponent(HofTable, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(closeBtn)))
					.addContainerGap(207, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(closeBtn)
					.addGap(10)
					.addComponent(HofTable, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		
		// import fix most probably
		
		closeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
			//	new PlayerHomePage(Block223Application.getCurrentUserRole().toString()).setVisible(true); // will be solved when I push
			}
		});
		
		refreshHof(); 
	}
	
	String error = null; 
	
	private void refreshHof() {
		
		if (error == null || error.length() == 0) {
		HofDtm = new DefaultTableModel(0, 0); // so each time a new JTable is created with 0 rows. 
		HofDtm.setColumnIdentifiers(HofColumnNames);
		HofTable.setModel(HofDtm); // don't know what this does 
		
		TOHallOfFame toHof = null;
		try { 
			 toHof = Block223Controller.getHallOfFameWithGameName(this.getGameName()); 
		} catch(InvalidInputException e) {
			error = e.getMessage(); 
		}
		List<TOHallOfFameEntry> list = toHof.getEntries(); 
		int count =1;
		for(TOHallOfFameEntry to : list) {
		String object[] = { Integer.toString(count), to.getPlayername(), Integer.toString(to.getScore())}; 
		HofDtm.addRow(object);
		
		}
		
		}
		
	}
	
	
	
	
	
}
