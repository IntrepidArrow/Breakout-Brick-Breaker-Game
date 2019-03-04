package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Paddle;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.view.EditLevel;

public class Block223Application {

	private static Block223 block223;
	private static Game currentGame;
	private static UserRole currentUserRole;
	
	public static void main(String[] args) {
		// initialize
		block223 = getBlock223();
		setCurrentUserRole(new Admin("albert", block223));
		Game game = null;
		setCurrentGame(game);
		game = new Game("revvy", 10, (Admin) currentUserRole, 5 ,4, 5.0, 6,3, block223); //null are block and paddle
		setCurrentGame(game);
		
		try {
			Block223Controller.setGameDetails(3, 10, 5 ,4, 5.0 , 6,3);
			Block223Controller.addBlock(30, 30, 10, 40);
			Block223Controller.addBlock(40, 30, 10, 65);
			Block223Controller.addBlock(30, 40, 10, 93);
			Block223Controller.addBlock(30, 71, 10, 10);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// start UI
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditLevel().frame.setVisible(true);
            }
        });
	}

	public static Block223 getBlock223() {
		if(block223 == null) {
			//load model
			block223 = Block223Persistence.load();
		}
		//will directly return class variable if it is not empty 
		return block223;
	}

	public static Block223 resetBlock223() {
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
