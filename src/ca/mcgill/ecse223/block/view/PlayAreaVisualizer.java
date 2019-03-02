package ca.mcgill.ecse223.block.view;

import javax.swing.JPanel;

import ca.mcgill.ecse223.block.controller.TOGridCell;

import java.awt.BasicStroke;
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
	private int selectedGridCell;
	
	public PlayAreaVisualizer() {
		super();
		init();
	}
	
	private void init(){
		level = 0;
		gridCells = new HashMap<Rectangle2D, TOGridCell>();
		selectedGridCell = 0;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				for (Rectangle2D square : squares) {
					if (square.contains(x, y)) {
						selectedGridCell = gridCells.get(square).getId();
						break;
					}
				}
				repaint();
			}
		});
	}
	
	public void setLevel(int aLevel) {
		this.level = aLevel;
		gridCells = new HashMap<Rectangle2D, TOGridCell>();
		selectedGridCell = 0;
		repaint();
	}

	private void doDrawing(Graphics G) {
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

	
	

}
