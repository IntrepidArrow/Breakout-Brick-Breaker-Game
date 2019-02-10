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

	public static void moveBlock() {

	}

	/*
	 * private static Block findBlock(int idBlock, String idGame) { Block foundBlock
	 * = null; Game game = findGame(idGame); for (Block block : game.getBlocks() ) {
	 * if (block.getId() == idBlock) { foundBlock = block; break; } } return
	 * foundBlock; }
	 * 
	 * private static Game findGame(String idGame) { Game foundGame = null; for(Game
	 * game: block223.getGames) { if(game.getName() == idGame) { foundGame = game;
	 * break; } } return foundGame;
	 * 
	 * }
	 */
}
