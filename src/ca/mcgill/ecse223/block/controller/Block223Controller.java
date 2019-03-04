package ca.mcgill.ecse223.block.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Paddle;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;

public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************

	public static void createGame(String name) throws InvalidInputException {
		UserRole admin = Block223Application.getCurrentUserRole();

		if (!(admin instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to create a game.");

		Block223 block223 = Block223Application.getBlock223();

		if (name == null || name.length() == 0)
			throw new InvalidInputException("The name of a game must be specified.");

		if (block223.findGame(name) != null)
			throw new InvalidInputException("The name of a game must be unique.");

		new Game(name, 1, (Admin) admin, 1, 1, 1, 10, 10, block223);
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {

		String error = "";
		UserRole currentRole = Block223Application.getCurrentUserRole();
		if (!(currentRole instanceof Admin)) {
			error += "Admin privileges are required to position a block. ";
		} else if (currentRole instanceof Admin) {
			Admin currentAdmin = (Admin) currentRole;
			if (!currentAdmin.getGames().contains(Block223Application.getCurrentGame())) {
				error += "Only the admin who created the game can position a block. ";
			}
		}

		Game game = Block223Application.getCurrentGame();

		if (game == null) {
			error += "A game must be selected to define game settings. ";
		}
		if (nrLevels < 1 || nrLevels > 99) {
			error += "The number of levels must be between 1 and 99. ";
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		try {
			game.setNrBlocksPerLevel(nrBlocksPerLevel);
		} catch (RuntimeException e) {

			if (nrBlocksPerLevel <= 0) {

				throw new InvalidInputException("nrBlocksPerLevel is negative of zero");
			}
		}

		Ball ball = game.getBall();

		try {
			ball.setMinBallSpeedX(minBallSpeedX);
		} catch (RuntimeException e) {
			if (minBallSpeedX <= 0) {
				throw new InvalidInputException(" minBallSpeedX must be greater than zero");
			}
		}

		try {
			ball.setMinBallSpeedY(minBallSpeedY);
		} catch (RuntimeException e) {

			if (minBallSpeedY <= 0) {
				throw new InvalidInputException("minBallSpeedY must be greater than zero");
			}

		}

		try {
			ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
		} catch (RuntimeException e) {

			if (ballSpeedIncreaseFactor <= 0) {
				throw new InvalidInputException("ballSpeedIncreaseFactor must be greater than zero");
			}
		}

		Paddle paddle = game.getPaddle();

		try {
			paddle.setMaxPaddleLength(maxPaddleLength);

		} catch (RuntimeException e) {
			if ((maxPaddleLength <= 0) || (maxPaddleLength >= 400)) {
				throw new InvalidInputException(
						"the maximum length of the paddle must be greater than 0 and less or equal to 400");
			}
		}
		try {

			paddle.setMinPaddleLength(minPaddleLength);

		} catch (RuntimeException e) {
			if (minPaddleLength <= 0) {

				throw new InvalidInputException("the minPaddleLength of the paddle must be greater than 0 ");
			}
		}

		if (nrLevels > game.getLevels().size()) {

			for (int i = game.getLevels().size(); i <= nrLevels; i++) {

				game.addLevel();
			}
		} else if (nrLevels < game.getLevels().size()) {

			for (int k = nrLevels; k <= game.getLevels().size(); k++) {
				Level level = game.getLevel(game.getLevels().size() - 1);
				level.delete();
			}
		}

		Block223Application.setCurrentGame(game);
		return;

	}

	public static void deleteGame(String name) throws InvalidInputException {

		UserRole admin = Block223Application.getCurrentUserRole();

		if (!(admin instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to delete a game.");

		Block223 block223 = Block223Application.getBlock223();

		Game game = block223.findGame(name);

		if (game == null)
			return;

		if (!game.getAdmin().equals(admin))
			throw new InvalidInputException("Only the admin who created the game can delete the game.");

		game.delete();
		Block223Persistence.save(block223);
	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		String error = "";
		UserRole currentRole = Block223Application.getCurrentUserRole();
		if (!(currentRole instanceof Admin)) {
			error += "Admin privileges are required to position a block. ";
		} else if (currentRole instanceof Admin) {
			Admin currentAdmin = (Admin) currentRole;
			if (!currentAdmin.getGames().contains(Block223Application.getCurrentGame())) {
				error += "Only the admin who created the game can position a block. ";
			}
		}
		Game game = Block223Application.getCurrentGame();
		String currentName = game.getName();
		if (currentName.equals(name) == false) {

			game.setName(name);
		}

		// TODO check if this is correct '
		try {
			setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY,
					ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {

		String error = "";
		// InavlidInputException checks
		// Check if this is the right way to do the invalid input exception
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) { // if current user role is not set to Admin
			error += "Admin privileges are required to add a block. ";
		}
		if (Block223Application.getCurrentGame() == null) { // if the current game is not set
			error += "A game must be selected to add a block. ";
		}
		if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error += "Only the admin who created the game can add a block. ";
		}
		for (Block block : Block223Application.getCurrentGame().getBlocks()) { // Another block exists in the current
																				// game with same color attributes
			if ((block.getRed() == red) && (block.getGreen() == green) && (block.getBlue() == blue)) {
				error += "A Block with the same color already exists for the game. ";
			}
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Game game = Block223Application.getCurrentGame();
		try {
			System.out.println("Hey there 1");
			Block block = new Block(red, green, blue, points, game);
			System.out.println("Hey there 2");
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void deleteBlock(int id) throws InvalidInputException {
		String error = "";
		// InavlidInputException checks
		// Check if this is the right way to do the invalid input exception
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) { // if current user role is not set to Admin
			error += "Admin privileges are required to delete a block. ";
		}
		if (Block223Application.getCurrentGame() == null) { // if the current game is not set
			error += "A game must be selected to delete a block. ";
		}
		if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) { // if current
																											// user role
																											// is not
																											// the admin
																											// of the
																											// game
			error += "Only the admin who created the game can delete a block. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		// Main method body
		Game game = Block223Application.getCurrentGame();
		Block block = game.findBlock(id);
		if (block != null) {
			block.delete();
			try {
				Block223Persistence.save(Block223Application.getBlock223());
			} catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
		}
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
		String error = "";

		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) { // if current user role is not set to Admin
			error += "Admin privileges are required to add a block. ";
		}
		if (Block223Application.getCurrentGame() == null) { // if the current game is not set
			error += "A game must be selected to add a block. ";
		}
		if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error += "Only the admin who created the game can add a block. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Game game = Block223Application.getCurrentGame();
		Block updatedBlock = game.findBlock(id);
		if(updatedBlock == null) {
			throw new InvalidInputException("The block does not exist.");
		}
		try {
			updatedBlock.setRed(red);
			updatedBlock.setGreen(green);
			updatedBlock.setBlue(blue);
			updatedBlock.setPoints(points);

		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		String error = "";

		UserRole currentRole = Block223Application.getCurrentUserRole();
		if (!(currentRole instanceof Admin)) {
			error += "Admin privileges are required to position a block.";
		} else if (currentRole instanceof Admin) {
			Admin currentAdmin = (Admin) currentRole;
			if (!currentAdmin.getGames().contains(Block223Application.getCurrentGame())) {
				error += "Only the admin who created the game can position a block. ";
			}
		}

		if (Block223Application.getCurrentGame() == null) {
			error += "A game must be selected to position a block. ";
		}
		Game game = Block223Application.getCurrentGame();

		// check level number
		if (level < 1 || level > game.getLevels().size()) {
			error += "Level " + level + " does not exist for the game.";
		}
		Level actualLevel = game.getLevel(level);

		// check if max number of blocks
		if (actualLevel.getBlockAssignments().size() + 1 == game.getNrBlocksPerLevel()) {
			error += "The number of blocks has reached the maximum number" + game.getNrBlocksPerLevel()
					+ " allowed for this game. ";
		}

		// check block exist
		Block block = game.findBlock(id);
		if (block == null) {
			error = error + "The block does not exist. ";
		}

		// check if position is taken
		BlockAssignment takenBlockAssignment = actualLevel.findBlockAssignment(gridHorizontalPosition,
				gridVerticalPosition);
		if (takenBlockAssignment != null) {
			error += "A block already exists at location" + gridHorizontalPosition + "/" + gridVerticalPosition + ".";
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		try {
			actualLevel.addBlockAssignment(gridHorizontalPosition, gridVerticalPosition, block, game);

		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {

		String error = "";
		UserRole currentRole = Block223Application.getCurrentUserRole();

		if (!(currentRole instanceof Admin)) {
			error += "Admin privileges are required to move a block. ";
		} else if (currentRole instanceof Admin) {
			Admin currentAdmin = (Admin) currentRole;
			if (!currentAdmin.getGames().contains(Block223Application.getCurrentGame())) {
				error += "Only the admin who created the game can move a block. ";
			}
		}

		if (Block223Application.getCurrentGame() == null) {
			error += "A game must be selected to position a block. ";
		}

		Game game = Block223Application.getCurrentGame();

		// check level number
		if (level < 1 || level > game.getLevels().size()) {
			error += "Level " + level + " does not exist for the game.";
		}

		Level actualLevel = game.getLevel(level);

		// check if old block assignment exists at old position
		BlockAssignment blockassignment = actualLevel.findBlockAssignment(oldGridHorizontalPosition,
				oldGridVerticalPosition);
		if (blockassignment == null) {
			error += error + "A block does not exist at location" + oldGridHorizontalPosition + "/"
					+ oldGridVerticalPosition + ".";
		}

		// check if position is taken
		BlockAssignment takenBlockassignemnt = actualLevel.findBlockAssignment(newGridHorizontalPosition,
				newGridVerticalPosition);
		if (takenBlockassignemnt != null) {
			error += error + " A block already exists at location" + newGridHorizontalPosition + "/"
					+ newGridVerticalPosition + ".";
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		try {
			blockassignment.setGridHorizontalPosition(newGridHorizontalPosition);
			blockassignment.setGridVerticalPosition(newGridVerticalPosition);
		} catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}

	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	}

	public static void saveGame() throws InvalidInputException {
		String error = "";
		UserRole currentRole = Block223Application.getCurrentUserRole();

		if (!(currentRole instanceof Admin)) {
			error += "Admin privileges are required to access game information. ";
		} else if (currentRole instanceof Admin) {
			Admin currentAdmin = (Admin) currentRole;
			if (!currentAdmin.getGames().contains(Block223Application.getCurrentGame())) {
				error += "Only the admin who created the game can save it. ";
			}
		}

		if (Block223Application.getCurrentGame() == null) {
			error += "A game must be selected to access its information.";
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Block223 block223 = Block223Application.getBlock223();
		try {
			Block223Persistence.save(block223);
		} catch (RuntimeException e) {
			throw new InvalidInputException("An error has occured while saving the game.");
		}

		return;

	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
		String error = "";
		if (Block223Application.getCurrentUserRole() != null) {
			error += "Cannot register a new user while a user is logged in.";
		}
		if (playerPassword == adminPassword) {
			error += "The passwords have to be different.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Block223 block223 = Block223Application.getBlock223();
		Player player = new Player(playerPassword, block223);
		User user = null;
		try {
			user = new User(username, block223, player);
		} catch (Exception e) {
			block223.removeRole(player);
			throw new InvalidInputException(e.toString());

		}
		if (!(adminPassword == null || adminPassword.isEmpty())) {
			Admin admin = new Admin(adminPassword, block223);
			user.addRole(admin);
		}
		Block223Persistence.save(block223);

	}

	public static void login(String username, String password) throws InvalidInputException {

		String error = "";
		if (Block223Application.getCurrentUserRole() != null) {
			error += "Cannot register a new user while a user is logged in.";
		}

		Block223 block223 = Block223Application.resetBlock223();
		User user = User.getWithUsername(username);
		if (user == null) {
			error += "The username and password do not match.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		List<UserRole> roles = user.getRoles();
		for (UserRole r : roles) {
			String rolePassword = r.getPassword();
			if (rolePassword.equals(password)) {
				Block223Application.setCurrentUserRole(r);
				System.out.println("logged in as " + (r instanceof Admin ? "admin" : "player"));

				return;
			}
		}

		throw new InvalidInputException("The username and password do not match..");

	}

	public static void logout() {
		Block223Application.setCurrentUserRole(null);

	}

	// ****************************
	// Helper methods
	// ****************************
	public static boolean adminOwnsGame(Admin admin,String gameName) {
		List<Game> gList=admin.getGames();
		for(Game g:gList) {
			if(g.getName()==gameName) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public static void selectGame(String name) throws InvalidInputException {
		String error = "";

		UserRole currentRole = Block223Application.getCurrentUserRole();
		if (!(currentRole instanceof Admin)) {
			error += "Admin privileges are required to position a block. ";
		} else if (currentRole instanceof Admin) {
			Admin currentAdmin = (Admin) currentRole;
			if (!adminOwnsGame(currentAdmin,name)) {
				error += "Only the admin who created the game can position a block. ";
			}
		}
		Block223 block223 = Block223Application.getBlock223();

		Game selectedGame =	block223.findGame(name);
		if (selectedGame == null) {
			error += "A game with name " + name + " does not exist.";
		}

		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Block223Application.setCurrentGame(selectedGame);
	}

	public static String getSHA512(String passwordToHash, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	// ****************************
	// Query methods
	// ****************************

	public static List<TOGame> getDesignableGames() throws InvalidInputException {

		UserRole admin = Block223Application.getCurrentUserRole();

		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}

		Block223 block223 = Block223Application.getBlock223();
		List<TOGame> result = new ArrayList<>();
		List<Game> games = block223.getGames();

		for (Game game : games) {
			Admin gameAdmin = game.getAdmin();

			if (gameAdmin.equals(admin)) {
				TOGame to = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(),
						game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(),
						game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(),
						game.getPaddle().getMinPaddleLength());
				result.add(to);
			}
		}

		return result;
	}

	public static TOGame getCurrentDesignableGame() throws InvalidInputException {

		String error = "";
		// InavlidInputException checks
		// Check if this is the right way to do the invalid input exception
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) { // if current user role is not set to Admin
			error += "Admin privileges are required to access game information. ";
		}
		if (Block223Application.getCurrentGame() == null) { // if the current game is not set
			error += "A game must be selected to access its information. ";
		}
		if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) { // if current
																											// user role
																											// is not
																											// the admin
																											// of the
																											// game
			error += "Only the admin who created the game can access its information. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Game game = Block223Application.getCurrentGame();
		TOGame to = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(),
				game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(),
				game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(),
				game.getPaddle().getMinPaddleLength());

		return to;
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
		String error = "";
		// InavlidInputException checks
		// Check if this is the right way to do the invalid input exception
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) { // if current user role is not set to Admin
			error += "Admin privileges are required to access game information. ";
		}
		if (Block223Application.getCurrentGame() == null) { // if the current game is not set
			error += "A game must be selected to access its information. ";
		}
		if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) { // if current
																											// user role
																											// is not
																											// the admin
																											// of the
																											// game
			error += "Only the admin who created the game can access its information. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Game game = Block223Application.getCurrentGame();
		List<TOBlock> result = new ArrayList<>();
		List<Block> blocks = game.getBlocks();

		for (Block block : blocks) {
			TOBlock to = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(),
					block.getPoints());

			result.add(to);
		}

		return result;

	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
		String error = "";
		// InavlidInputException checks
		// Check if this is the right way to do the invalid input exception
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) { // if current user role is not set to Admin
			error += "Admin privileges are required to access game information. ";
		}
		if (Block223Application.getCurrentGame() == null) { // if the current game is not set
			error += "A game must be selected to access its information. ";
		}
		if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) { // if current
																											// user role
																											// is not
																											// the admin
																											// of the
																											// game
			error += "Only the admin who created the game can access its information. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Game game = Block223Application.getCurrentGame();
		Block block = game.findBlock(id);
		if (block == null) {
			throw new InvalidInputException("The block does not exist. ");
		}

		TOBlock to = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());

		return to;
	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {

		String error = "";
		UserRole currentRole = Block223Application.getCurrentUserRole();

		if (!(currentRole instanceof Admin)) {
			error += "Admin privileges are required to access game information. ";
		} else if (currentRole instanceof Admin) {
			Admin currentAdmin = (Admin) currentRole;
			if (!currentAdmin.getGames().contains(Block223Application.getCurrentGame())) {
				error += "Only the admin who created the game can access its information. ";
			}
		}

		if (Block223Application.getCurrentGame() == null) {
			error += "A game must be selected to access its information.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		List<TOGridCell> result = new ArrayList<TOGridCell>();
		Game game = Block223Application.getCurrentGame();
		Level actualLevel = null;
		try {
			actualLevel = game.getLevel(level - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException("Level " + level + " does not exist for the game.");
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
		UserRole userRole = Block223Application.getCurrentUserRole();
		TOUserMode.Mode mode = null;

		if (userRole == null) {
			mode = TOUserMode.Mode.None;
		} else if (userRole instanceof Player) {
			mode = TOUserMode.Mode.Play;

		} else if (userRole instanceof Admin) {
			mode = TOUserMode.Mode.Design;

		}
		TOUserMode userMode = new TOUserMode(mode);
		return userMode;
	}
}