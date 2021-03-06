package ca.mcgill.ecse223.block.persistence;

import ca.mcgill.ecse223.block.model.Block223;

public class Block223Persistence {
	private static String filename = "data.block";

	public static void save(Block223 block223) {
		PersistenceObjectStream.setFilename(filename);
		PersistenceObjectStream.serialize(block223);
	}
	public static void reset() {
		PersistenceObjectStream.setFilename(filename);
		PersistenceObjectStream.serialize(null);
	}

	public static Block223 load() {
		PersistenceObjectStream.setFilename(filename);
		Block223 block223 = (Block223) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty BTMS
		if (block223 == null) {
			block223 = new Block223();
			System.out.println("nothing found");
		} else {
			// TODO: define reinitialize
			block223.reinitialize();
		}
		return block223;
	}

	public static void setFilename(String newFilename) {
		filename = newFilename;
	}

}
