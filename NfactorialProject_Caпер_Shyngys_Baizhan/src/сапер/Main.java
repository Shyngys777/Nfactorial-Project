package сапер;

import сапер.gui.DisplayManager;

public class Main {

	public static void main(String[] args) {
		System.out.println("Starting...");
		DisplayManager.createWindow();
		DisplayManager.addMainMenu();
		DisplayManager.startGame();
		System.out.println("Game Started");
	}
}
