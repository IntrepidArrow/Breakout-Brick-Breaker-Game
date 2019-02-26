package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;

public class Block223Application {

	private static Block223 block223;
	private static Game currentGame;
	private static UserRole currentUserRole;

	public static Block223 getBlock223() {
		if(block223 == null) {
			block223 = new Block223();
		}
		
		//will directly return class variable if it is not empty 
		return block223;
	}

	public Block223 resetBlock223() {
		// TODO implement this method,
		// a resetBlock223() method that forces a load from the file and returns the
		// root block223 object,
		block223 = Block223Persistence.load();
		return block223;
	}

	public static void setCurrentUserRole(UserRole aUserRole) {
		//sets the currently logged in user role,
		currentUserRole = aUserRole;
	}

	public static UserRole getCurrentUserRole() {
		//returns the currently logged in user role,
		return currentUserRole;
	}

	public static void setCurrentGame(Game aGame) {
		// TODO implement this method
		// a setCurrentGame(Game aGame) method that remembers the current game,
		currentGame = aGame;
	}

	public static Game getCurrentGame() {
		// TODO implement this method
		// a getCurrentGame() method that return the current game.
		return currentGame;
	}

}
