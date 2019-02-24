package ca.mcgill.ecse223.block.controller;

import java.util.List;

import ca.mcgill.ecse.btms.controller.InvalidInputException;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;

class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
	}

	public static void deleteGame(String name) throws InvalidInputException {
	}

	public static void selectGame(String name) throws InvalidInputException {
	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void deleteBlock(int id) throws InvalidInputException {
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		String error = "";
		if (Block223Application.getCurrentUserRole() == "AdminRole") {
			error = "Admin privileges are required to position a block. ";
		}

		if (Block223Application.getCurrentGame() == null) {
			error += "A game must be selected to position a block. ";
		}

		/*
		 * if(Block223Application.getCurrentUserRole() ==
		 * Block223Application.getCurrentGame().getAdmin(). ){ error +=
		 * "Only the admin who created the game can position a block." }
		 */
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Game game = Block223Application.getCurrentGame();
		// check level number
		Level actualLevel = game.getLevel(level);
		// check, block does not exist invalidinputexception
		Block block = game.findBlock(id);
		BlockAssignment blockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, actualLevel,
				block, game);

	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
		Game game = Block223Application.getCurrentGame();
		// check level number
		Level actualLevel = game.getLevel(level);
		BlockAssignment assignment = actualLevel.findBlock //define findblock
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	}

	public static void saveGame() throws InvalidInputException {
	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
	}

	public static void login(String username, String password) throws InvalidInputException {
	}

	public static void logout() {
	}

	// ****************************
	// Query methods
	// ****************************
	public static List<TOGame> getDesignableGames() {
	}

	public static TOGame getCurrentDesignableGame() {
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() {

	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {

	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		String error = "";

		if (Block223Application.getCurrentUserRole() == "AdminRole") {
			error = "Admin privileges are required to access game information. ";
		}

		if (Block223Application.getCurrentGame() == null) {
			error += "A game must be selected to access its information.";
		}

		// if(Block223Application.getCurrentUserRole() ==
		// Block223Application.getCurrentGame().getAdmin(). )
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Game game = Block223Application.getCurrentGame();
		List<TOGridCell> result = new List<TOGridCell>();
		// TODO fix the throw, create List
		try {
			Level actualLevel = game.getLevel(level);
		} catch (IndexOutOfBoundsException ex) {
			throw new IndexOutOfBoundsException("Level" + level + " does not exist for the game.");
		}

		List<BlockAssignment> assignments = actualLevel.getBlockAssignments();

		for (int i = 0; i < assignments.size(); i++) {
			BlockAssignment assignment = assignments.get(i);
			TOGridCell to = new TOGridCell(assignment.getGridHorizontalPosition(), assignment.getGridVerticalPosition(),
					assignment.getBlock().getId(), assignment.getBlock().getRed(), assignment.getBlock().getGreen(),
					assignment.getBlock().getBlue(), assignment.getBlock().getPoints());
			result.add(to);
		}

		return result;

	}

	public static TOUserMode getUserMode() {
	}

}
