package сапер.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import сапер.utils.Difficulty;
import сапер.utils.ResourceManager;

public class DisplayManager {

	private static int windowWidth;
	private static int windowHeight;
	
	private static JFrame window;
	private static JPanel mainMenu;
	private static JPanel gameBoard;

	public static void createWindow() {
		window = new JFrame("Игра Сапер");
		window.setBounds(100, 50, windowWidth, windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		System.out.println("Открытое Окно");
	}

	public static void addMainMenu() {
		mainMenu = new MainMenu();
		windowWidth = 580;
		windowHeight = 300;
		if(gameBoard!=null) window.remove(gameBoard);
		window.setSize(windowWidth, windowHeight);
		window.add(mainMenu);
		mainMenu.requestFocusInWindow();
		System.out.println("Меню");
	}

	public static void addGameBoard(Difficulty diff) {
		ResourceManager.init();
		gameBoard = new GameBoard(diff);
		windowWidth = diff.getWidth()*36+5;
		windowHeight = diff.getHeight()*36+30+60;
		window.remove(mainMenu);
		window.setSize(windowWidth, windowHeight);
		window.add(gameBoard);
		gameBoard.requestFocusInWindow();
		System.out.println("Игровая панель");
	}

	public static void startGame() {
		window.setVisible(true);
	}

	public static int getWindowWidth() {
		return windowWidth;
	}

	public static int getWindowHeight() {
		return windowHeight;
	}
}
