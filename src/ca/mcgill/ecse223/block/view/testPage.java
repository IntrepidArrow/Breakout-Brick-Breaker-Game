package ca.mcgill.ecse223.block.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOHallOfFame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;

public class testPage extends JFrame {

	private String gameName; 
	private static final long serialVersionUID = -4426310869335015542L;
	private JScrollPane HofScrollPane; 
	private DefaultTableModel HofDtm; 
	private String HofColumnNames[] = {"Position", "Name", "Score"};
	private JTable HofTable;
	private JButton closeBtn;
	private static final int HEIGHT_OVERVIEW_TABLE = 200;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					testPage window = new testPage();
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
	 
		public testPage(String gameName) {
			this.gameName = gameName; 
			initialize();
		}
		
		private String getGameName(){
			return this.gameName;
		}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		HofTable = new JTable();

		closeBtn = new JButton("Close");
		HofScrollPane = new JScrollPane(HofTable);
		this.add(HofScrollPane); 
		Dimension d = HofTable.getPreferredSize(); 
		HofScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
		HofScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
		
		
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(closeBtn))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(HofTable, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
				.addComponent(HofScrollPane)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(closeBtn)
					.addGap(3)
					.addComponent(HofTable, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
					.addGap(27))
				.addComponent(HofScrollPane)
		);
		this.getContentPane().setLayout(groupLayout);
		closeBtn.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
				new PlayerHomePage(Block223Application.getCurrentUserRole().toString()).setVisible(true); // will be solved when I push
			}
		});
		
		refreshHof(); 
	}
	
	String error = null;
private void refreshHof() {
		
		if (error == null || error.length() == 0) {
		HofDtm = new DefaultTableModel(0, 0); // so each time a new JTable is created with 0 rows. 
		HofDtm.setColumnIdentifiers(HofColumnNames);
		HofTable.setModel(HofDtm); 
		
//		TOHallOfFame toHof = null;		
//		try { 
//			 toHof = Block223Controller.getHallOfFameWithGameName(this.getGameName()); 
//		} catch(InvalidInputException e) {
//			error = e.getMessage(); 
//		}
//		
//		List<TOHallOfFameEntry> list = toHof.getEntries(); 
//	int count =1;
//		for(TOHallOfFameEntry to : list) {
//		Object obj[] = { Integer.toString(count), to.getPlayername(), Integer.toString(to.getScore())}; 
//		HofDtm.addRow(obj);
//		count++;
//		
		}
//		
//		}
	Dimension d = HofTable.getPreferredSize();
	HofScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));		
	}
	
}
