package ca.mcgill.ecse223.block.application;


import ca.mcgill.ecse223.block.model.Block223;

public class BlockApplication {
	
	private static Block223 blockApp;

	public static Block223 getBlockApp() {
		if (blockApp == null) {
			blockApp = new Block223();
		}
		//returns existing an existing blockApplication if there exists a block application prior to check 
 		return blockApp;
	}
}
