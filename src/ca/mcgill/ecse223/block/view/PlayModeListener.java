package ca.mcgill.ecse223.block.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

public class PlayModeListener implements KeyListener {

	/**
	 * String input from keyboard - marked as volatile since it is shared by two
	 * threads
	 */
	private volatile String keyString = "";

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		try {
			keyInputs(e);
		} catch (InvalidInputException ex) {
			System.out.println(ex);
		}
	}

	private synchronized String keyInputs(KeyEvent e) throws InvalidInputException {
		int location = e.getKeyCode();
		if (location == KeyEvent.VK_LEFT) {
			keyString += "l";
		} else if (location == KeyEvent.VK_RIGHT) {
			keyString += "r";
		} else if (location == KeyEvent.VK_SPACE) {
			keyString += " ";
		} else {
			// ignore all other keys
		}
		return keyString;
	}

	/**
	 * Takes key inputs and clears the input string. marked as synchronized to make
	 * the key inputs as thread-safe
	 */
	public synchronized String takeInputs() {
		String passString = keyString;
		keyString = "";
		return passString;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

}
