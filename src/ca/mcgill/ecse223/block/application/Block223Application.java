package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.UserRole;

public class Block223Application {

	private static Block223 block223;

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
		return new Block223();
	}

	public boolean setCurrentUserRole(UserRole aUserRole) {
		// TODO implement this method
		// a setCurrentUserRole(UserRole aUserRole) method that remembers the currently
		// logged in user role,

		return true;
	}

	public static UserRole getCurrentUserRole() {
		UserRole userRole = null;
		// TODO implement this method
		// a getCurrentUserRole() method that returns the currently logged in user role,
		return userRole;
	}

	public boolean setCurrentGame(Game aGame) {
		// TODO implement this method
		// a setCurrentGame(Game aGame) method that remembers the current game,
		return true;
	}

	public static Game getCurrentGame() {
		// TODO implement this method
		// a getCurrentGame() method that return the current game.
		return new Game();
	}

}
