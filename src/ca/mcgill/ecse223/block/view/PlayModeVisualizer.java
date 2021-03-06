package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOCurrentBlock;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;
import ca.mcgill.ecse223.block.controller.TOGridCell;

public class PlayModeVisualizer extends JPanel  {

	// Drawing constant
	private List<Rectangle2D> squares = new ArrayList<Rectangle2D>();
	private static final int PLAY_AREA_SIDE = 390;
	private static final int WALL_PADDING = 10;
	private static final int COLUMNS_PADDING = 5;
	private static final int ROW_PADDING = 2;
	private static final int BALL_DIAMETER = 10;
	private static final int BALL_RADIUS = 5;
	private static final int PADDLE_WIDTH = 5;
	private static final int PADDLE_VERTICAL_DISTANCE = 30;
	private static final int BLOCK_SIDE = 20;
	
	//played game elements
	public TOCurrentlyPlayedGame pGame;
	public List<TOCurrentBlock> blocks ;

	public PlayModeVisualizer() {
		super();
	}
	

	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		// draw paddle
		g2d.setFont(new Font("Calibri", Font.PLAIN, 15)); 

		g2d.drawString("Lives: "+pGame.getLives()+" Score: "+pGame.getScore(), 5, 385);
		if(pGame.getPaused()) {
			g2d.drawString("Game Paused", 150, 385);
		}
		Rectangle2D paddle = new Rectangle2D.Float(pGame.getCurrentPaddleX(), PLAY_AREA_SIDE - PADDLE_VERTICAL_DISTANCE - PADDLE_WIDTH, pGame.getCurrentPaddleLength(), PADDLE_WIDTH);
		g2d.setPaint(Color.orange);
		g2d.fill(paddle);
		

		
		//draw blocks
		//System.out.println("Setting block");
//		System.out.println(pGame.getBlocks());
		for(TOCurrentBlock pBlock : pGame.getBlocks()) {
			int xPixel = pBlock.getX();
			int yPixel = pBlock.getY();
			//System.out.println(xPixel+" "+yPixel+" "+pBlock.getX()+" "+pBlock.getY());
			Rectangle2D block = new Rectangle2D.Float(xPixel,yPixel,20,20); 
			//g2d.setColor(new Color(pBlock.getRed(), pBlock.getGreen(), pBlock.getBlue()));
			g2d.setColor(new Color(pBlock.getRed(), pBlock.getGreen(), pBlock.getBlue()));
	
			g2d.fill(block);
		}
		
		//draw ball
		Ellipse2D circle = new Ellipse2D.Float(pGame.getCurrentBallX() - BALL_RADIUS, pGame.getCurrentBallY() - BALL_RADIUS,BALL_DIAMETER , BALL_DIAMETER);
		g2d.setPaint(Color.RED);
		g2d.fill(circle);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(pGame!=null) {
			doDrawing(g);
		}
	}

	public void refreshDrawing(TOCurrentlyPlayedGame apGame) {
		// TODO Auto-generated method stub
		pGame = apGame;
		repaint();
		
	}

	
	
	
}
