package ca.mcgill.ecse223.block.controller;

import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.Block223Application;;

public class Block223Controller {

	// block is already created, just position it - Albert Assouad 
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
	
	//Feature 5 and 6 - Abhimukth Chaudhuri
	//Feature 5: Add a block to the game so it can be used in a level 
	public static void createBlock(int redVal, int greenVal, int blueVal, int points, String gameName) {
		
	}
	public static void deleteBlock(int id) {
		
		//method uses helper method: findBlock(id) to find the block that the admin wants to delete from the game. 
	}
	
	
	//Query methods
	//getBlocks query method - Abhimukth Chaudhuri
	public static List<TOBlock> getBlocks(String gameName){
		
		//Possible implementation for the above query method 
//		ArrayList<TOBlock> blocks = new ArrayList<>();
//		Game currentGame = Game.getWithName(gameName);
//		for(Block block : currentGame.getBlocks()) {
//			TOBlock toBlock = new TOBlock(block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
//			blocks.add(toBlock);
//		}
//		return blocks;
//		
		return null;
	}
	
	//Helper methods 
	//Finding a specific block by ID - Abhimukth Chaudhuri
	private static Block findBlock(int id, String gameName) {
		Game game = Game.getWithName(gameName);
		Block foundBlock = null;
		for(Block block : game.getBlocks()) {
			if(block.getId() == id) {
				foundBlock = block;
				break;
			}
		}
		return foundBlock;
	}


	//TODO: Confirm with group and see why this method is here in the controller class 
//	public static void moveBlock() {
//
//		
//	}
}
