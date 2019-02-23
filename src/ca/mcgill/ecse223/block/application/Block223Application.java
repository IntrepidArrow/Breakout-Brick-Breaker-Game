package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.UserRole;

public class Block223Application {

	private static Block223 app;
	
	

	public Block223 getBlock223() {
		//TODO implement this method, 
		//a getBlock223() method that returns the root block223 object
		return new Block223();
	}
	
	public Block223 resetBlock223() {
		//TODO implement this method, 
		//a resetBlock223() method that forces a load from the file and returns the root block223 object,
		return new Block223();
	}
	
	public boolean setCurrentUserRole(UserRole aUserRole) {
		//TODO implement this method
		//a setCurrentUserRole(UserRole aUserRole) method that remembers the currently logged in user role,
		
		return true;
	}
	
	public static String getCurrentUserRole() {
		//TODO implement this method
		//a getCurrentUserRole() method that returns the currently logged in user role,
		return "AdminRole";
	}
	
	public boolean setCurrentGame(Game aGame) {
		//TODO implement this method
		//a setCurrentGame(Game aGame) method that remembers the current game,
		return true;
	}
	
	public static Game getCurrentGame() {
		//TODO implement this method
		//a getCurrentGame() method that return the current game.
		return new Game();
	}
	
	
}
