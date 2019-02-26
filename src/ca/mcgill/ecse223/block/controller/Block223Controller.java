package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;



public class Block223Controller {

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
		String error = "";

		UserRole currentRole=Block223Application.getCurrentUserRole();
		if(!( currentRole instanceof Admin)) {
			error += "Admin privileges are required to position a block. ";
		}
		else if(currentRole instanceof Admin){
			Admin currentAdmin=(Admin) currentRole;
			if(!currentAdmin.getGames().contains(Block223Application.getCurrentGame())) {
				error += "Only the admin who created the game can position a block. ";
			}
		}

		Game game = Game.getWithName(name);
		if(Block223Application.getCurrentGame() == null) {
			error += "A game with name " + name + " does not exist.";
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Block223Application.setCurrentGame(game);
	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {

		String error = "";
		//InavlidInputException checks
		//Check if this is the right way to do the invalid input exception
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {	//if current user role is not set to Admin
			error += "Admin privileges are required to add a block. ";
		}
		if(Block223Application.getCurrentGame() == null) {	//if the current game is not set 
			error += "A game must be selected to add a block. ";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {	//if current user role is not the admin of the game
			error += "Only the admin who created the game can add a block. ";
		}
		for(Block block : Block223Application.getCurrentGame().getBlocks()) {	//Another block exists in the current game with same color attributes 
			if((block.getRed() == red) && (block.getGreen()==green) && (block.getBlue() == blue)) {
				error += "A Block with the same color already exists for the game. ";
			}
		}
		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Game game = Block223Application.getCurrentGame();
		try {
			game.addBlock(red, green, blue, points);
			Block223Persistence.save(Block223Application.getBlock223());
		}
		catch (RuntimeException e) {
			if(error.equals("Cannot set red color due to invalid input")) {
				error = "Red must be between 0 and 255.";
			}
			if(error.equals("Cannot set green color due to invalid input")) {
				error = "Green must be between 0 and 255.";
			}
			if(error.equals("Cannot set blue color due to invalid input")) {
				error = "Blue must be between 0 and 255.";
			}
			if(error.equals("Cannot set points value due to invalid input")) {
				error = "Points must be beween 1 and 1000.";
			}
			throw new InvalidInputException(error);
		}
	}

	public static void deleteBlock(int id) throws InvalidInputException {
		String error = "";
		//InavlidInputException checks
		//Check if this is the right way to do the invalid input exception
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {	//if current user role is not set to Admin
			error += "Admin privileges are required to delete a block. ";
		}
		if(Block223Application.getCurrentGame() == null) {	//if the current game is not set 
			error += "A game must be selected to delete a block. ";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {	//if current user role is not the admin of the game
			error += "Only the admin who created the game can delete a block. ";
		}
		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		//Main method body
		Game game = Block223Application.getCurrentGame();
		Block block = game.findBlock(id);
		if(block != null) {
			block.delete();
			try {
				Block223Persistence.save(Block223Application.getBlock223());
			}
			catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
		}
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		String error = "";

		UserRole currentRole=Block223Application.getCurrentUserRole();
		if(!( currentRole instanceof Admin)) {
			error += "Admin privileges are required to position a block.";
		}
		else if(currentRole instanceof Admin){
			Admin currentAdmin=(Admin) currentRole;
			if(!currentAdmin.getGames().contains(Block223Application.getCurrentGame())) {
				error += "Only the admin who created the game can position a block. ";
			}
		}

		if(Block223Application.getCurrentGame() == null) {
			error += "A game must be selected to position a block. ";
		}
		Game game = Block223Application.getCurrentGame();

		// check level number
		if(level < 1 || level > game.getLevels().size()) {
			error += "Level " + level + " does not exist for the game.";
		}
		Level actualLevel = game.getLevel(level);

		//check if max number of blocks
		if(actualLevel.getBlockAssignments().size() + 1 == game.getNrBlocksPerLevel()) {
			error += "The number of blocks has reached the maximum number" + game.getNrBlocksPerLevel() +" allowed for this game. ";

		}

		//check block exist
		Block block = game.findBlock(id);
		if (block == null) {
			error = error + "The block does not exist. ";
		}

		//check if position is taken
		BlockAssignment takenBlockAssignment = actualLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition);
		if(takenBlockAssignment != null) {
			error += error +  "A block already exists at location" + gridHorizontalPosition + "/" + gridVerticalPosition + ".";
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		try {
			actualLevel.addBlockAssignment(gridHorizontalPosition,gridVerticalPosition,block,game);

		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}


	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {

		String error = "";
		UserRole currentRole=Block223Application.getCurrentUserRole();

		if(!( currentRole instanceof Admin)) {
			error += "Admin privileges are required to move a block. ";
		}
		else if(currentRole instanceof Admin){
			Admin currentAdmin=(Admin) currentRole;
			if(!currentAdmin.getGames().contains(Block223Application.getCurrentGame())) {
				error += "Only the admin who created the game can move a block. ";
			}
		}

		if(Block223Application.getCurrentGame() == null) {
			error += "A game must be selected to position a block. ";
		}

		Game game = Block223Application.getCurrentGame();

		// check level number
		if(level < 1 || level > game.getLevels().size()) {
			error += "Level " + level + " does not exist for the game.";
		}

		Level actualLevel = game.getLevel(level);

		//check if old block assignment exists at old position
		BlockAssignment blockassignment = actualLevel.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);
		if(blockassignment == null) {
			error += error + "A block does not exist at location" + oldGridHorizontalPosition + "/" + oldGridVerticalPosition + ".";
		}

		//check if position is taken
		BlockAssignment takenBlockassignemnt = actualLevel.findBlockAssignment(newGridHorizontalPosition, newGridVerticalPosition); 
		if(takenBlockassignemnt != null) {
			error += error + " A block already exists at location" + newGridHorizontalPosition + "/" + newGridVerticalPosition + ".";
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		try {
			blockassignment.setGridHorizontalPosition(newGridHorizontalPosition);
			blockassignment.setGridVerticalPosition(newGridVerticalPosition);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}		

	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	}

	public static void saveGame() throws InvalidInputException {
		String error = "";
		UserRole currentRole=Block223Application.getCurrentUserRole();

		if(!( currentRole instanceof Admin)) {
			error += "Admin privileges are required to access game information. ";
		}else if(currentRole instanceof Admin){
			Admin currentAdmin=(Admin) currentRole;
			if(!currentAdmin.getGames().contains(Block223Application.getCurrentGame())) {
				error += "Only the admin who created the game can save it. ";
			}
		}

		if(Block223Application.getCurrentGame() == null) {
			error += "A game must be selected to access its information.";
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Block223 block223 = Block223Application.getBlock223();
		try {
			Block223Persistence.save(block223);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException("An error has occured while saving the game.");
		}

		return;

	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
	}

	public static void login(String username, String password) throws InvalidInputException {
	}

	public static void logout() {
		Block223Application.setCurrentUserRole(null);

	}

	// ****************************
	// Query methods
	// ****************************
	public static List<TOGame> getDesignableGames() throws InvalidInputException {
		//Check if this is the right way to throw this invalid input exception
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)){	//if the current user role of the game is not set to admin 
			throw new InvalidInputException("Admin privileges are required to access game information");
		}
		//Method body
		Block223 block223 = Block223Application.getBlock223();
		UserRole admin = Block223Application.getCurrentUserRole();
		List<TOGame> result = new ArrayList<>();
		List<Game> games = block223.getGames();
		for(Game game : games) {
			Admin gameAdmin = game.getAdmin();

			if(gameAdmin.equals(admin)) {
				TOGame to = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(), game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(), 
						game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
				result.add(to);
			}
		}

		return result;
	}

	public static TOGame getCurrentDesignableGame() throws InvalidInputException {

		String error = "";
		//InavlidInputException checks
		//Check if this is the right way to do the invalid input exception
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {	//if current user role is not set to Admin
			error += "Admin privileges are required to access game information. ";
		}
		if(Block223Application.getCurrentGame() == null) {	//if the current game is not set 
			error += "A game must be selected to access its information. ";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {	//if current user role is not the admin of the game
			error += "Only the admin who created the game can access its information. ";
		}
		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Game game = Block223Application.getCurrentGame();
		TOGame to = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(), game.getBall().getMinBallSpeedX(),
				game.getBall().getMinBallSpeedY(), game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(), 
				game.getPaddle().getMinPaddleLength());

		return to;
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
		String error = "";
		//InavlidInputException checks
		//Check if this is the right way to do the invalid input exception
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {	//if current user role is not set to Admin
			error += "Admin privileges are required to access game information. ";
		}
		if(Block223Application.getCurrentGame() == null) {	//if the current game is not set 
			error += "A game must be selected to access its information. ";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {	//if current user role is not the admin of the game
			error += "Only the admin who created the game can access its information. ";
		}
		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Game game = Block223Application.getCurrentGame();
		List<TOBlock> result = new ArrayList<>();
		List<Block> blocks = game.getBlocks();

		for(Block block : blocks) {
			TOBlock to = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());

			result.add(to);
		}

		return result;

	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
		String error = "";
		//InavlidInputException checks
		//Check if this is the right way to do the invalid input exception
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {	//if current user role is not set to Admin
			error += "Admin privileges are required to access game information. ";
		}
		if(Block223Application.getCurrentGame() == null) {	//if the current game is not set 
			error += "A game must be selected to access its information. ";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {	//if current user role is not the admin of the game
			error += "Only the admin who created the game can access its information. ";
		}
		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Game game = Block223Application.getCurrentGame();
		Block block = game.findBlock(id);
		if(block == null) {
			throw new InvalidInputException("The block does not exist. ");
		}

		TOBlock to = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());

		return to;
	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {

		String error = "";
		UserRole currentRole=Block223Application.getCurrentUserRole();

		if(!( currentRole instanceof Admin)) {
			error += "Admin privileges are required to access game information. ";
		}else if(currentRole instanceof Admin){
			Admin currentAdmin=(Admin) currentRole;
			if(!currentAdmin.getGames().contains(Block223Application.getCurrentGame())) {
				error += "Only the admin who created the game can access its information. ";
			}
		}

		if(Block223Application.getCurrentGame() == null) {
			error += "A game must be selected to access its information.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Game game = Block223Application.getCurrentGame();
		List<TOGridCell> result = new ArrayList<TOGridCell>();
		Level actualLevel;
		try {
			actualLevel = game.getLevel(level);
			
		}
		catch( IndexOutOfBoundsException ex ){
			throw new InvalidInputException("Level" + level + " does not exist for the game.");
		}


			List<BlockAssignment> assignments = actualLevel.getBlockAssignments();

			for(int i = 0 ; i < assignments.size() ; i++) {
				BlockAssignment assignment = assignments.get(i);
				TOGridCell to = new TOGridCell(assignment.getGridHorizontalPosition(), assignment.getGridVerticalPosition(), assignment.getBlock().getId(),
						assignment.getBlock().getRed(), assignment.getBlock().getGreen(), assignment.getBlock().getBlue(), assignment.getBlock().getPoints());
				result.add(to);
			}
		
		return result;
	}

	public static TOUserMode getUserMode() {
		UserRole userRole=Block223Application.getCurrentUserRole();
		TOUserMode.Mode mode = null;

		if(userRole == null) {
			mode=TOUserMode.Mode.None;
		}
		else if(userRole instanceof Player) {
			mode=TOUserMode.Mode.Play;

		}
		else if(userRole instanceof Admin) {
			mode=TOUserMode.Mode.Design;

		}
		TOUserMode userMode= new TOUserMode(mode);
		return userMode;
	}
}