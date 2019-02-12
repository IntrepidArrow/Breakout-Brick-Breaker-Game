package ca.mcgill.ecse223.block.controller;

import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.application.Block223Application;;

public class Block223Controller {

	// block is already created, just position it
	public static void positionBlock(int id, int xPos, int yPos, String gameName) {
		Block223 block223 = Block223Application.getApp();
		/*
		 * conditions: number of blocks less than the limit per level check if there is
		 * already a block assignment at this pos create a blockAssignment and add it to
		 * a level block.addBlockAssignment(xpos,ypos,level,game)
		 */

		// get game, get level
		// level.addBlockAssignment(int aGridHorizontalPosition, int
		// aGridVerticalPosition, Block aBlock, Game aGame);

	}
	
	
	//Query methods
	
	//Finding a specific block by ID
	private static Block findBlock(int id, String gameName) {
		Game game = findGame(gameName);
		Block foundBlock = null;
		for(Block block : game.getBlocks()) {
			if(block.getId() == id) {
				foundBlock = block;
				break;
			}
		}
		return foundBlock;
	}
	
	private static Game findGame(String gameName) {
		Block223 app = Block223Application.getApp();
		Game foundGame = null;
		for(Game game : app.getGames()) {
			if(game.getName() == gameName) {
				foundGame = game;
				break;
			}
		}
		return foundGame;
	}

	//TODO: Confirm with group and see why this method is here in the controller class 
//	public static void moveBlock() {
//
//		
//	}
}
