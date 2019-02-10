package ca.mcgill.ecse223.block.application;

import ca.mcgill.ecse223.block.model.Block223;

public class Block223Application {

	private static Block223 app;

	public static Block223 getApp() {
		if (app == null) {
			app = new Block223();
		}
		// returns existing an existing blockApplication if there exists a block
		// application prior to check
		return app;
	}
}
