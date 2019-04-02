package ca.mcgill.ecse223.block.view;

import javax.swing.JPanel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGridCell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PlayAreaVisualizer extends JPanel {
	
	private static final long serialVersionUID = 5765543683246454L;
	
	// UI elements
	private List<Rectangle2D> squares = new ArrayList<Rectangle2D>();
	private static final int PLAY_AREA_SIDE = 390;
	private static final int WALL_PADDING = 10;
	private static final int COLUMNS_PADDING = 5;
	private static final int ROW_PADDING = 2;
	private static final int BALL_DIAMETER = 10;
	private static final int PADDLE_WIDTH = 5;
	private static final int PADDLE_VERTICAL_DISTANCE = 30;
	
	// Data elements
	private int level;
	private HashMap<Rectangle2D, TOGridCell> gridCells;
	private TOGridCell selectedGridCell;
	
	public PlayAreaVisualizer() {
		super();
		init();
	}
	
	private void init(){
		level = 0;
		gridCells = new HashMap<Rectangle2D, TOGridCell>();
		selectedGridCell = null;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				for (Rectangle2D square : squares) {
					if (square.contains(x, y)) {
						selectedGridCell = gridCells.get(square);
						break;
					}
				}
				repaint();
			}
		});
	}
	public TOGridCell getSelectedGridCell() {
		
		return selectedGridCell;
	}
	public void setLevel(int aLevel) {
		this.level = aLevel;
		gridCells = new HashMap<Rectangle2D, TOGridCell>();
		selectedGridCell = null;
		repaint();
	}

	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Rectangle2D paddle = new Rectangle2D.Float(180, PLAY_AREA_SIDE - PADDLE_VERTICAL_DISTANCE - PADDLE_WIDTH, 30, PADDLE_WIDTH);
		g2d.setPaint(Color.black);
		g2d.fill(paddle);
		if(level != 0) {
			squares.clear();
		try {
			for (TOGridCell gridCell : Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level)) {
				int xPixel = WALL_PADDING + (gridCell.getGridHorizontalPosition()-1)*(20+COLUMNS_PADDING);
				int yPixel = WALL_PADDING + (gridCell.getGridVerticalPosition()-1)*(20+ROW_PADDING);
			Rectangle2D block = new Rectangle2D.Float(xPixel,yPixel,20,20); 
			squares.add(block);
			g2d.setColor(new Color(gridCell.getRed(), gridCell.getGreen(), gridCell.getBlue()));
			if(selectedGridCell == gridCell) {
				g2d.draw(block);
			}
			else {
				g2d.fill(block);
			}
			
			}
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

	
	

}
