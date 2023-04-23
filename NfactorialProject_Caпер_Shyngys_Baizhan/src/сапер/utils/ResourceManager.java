package сапер.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceManager {
	
	public static BufferedImage tile;
	public static BufferedImage flaggedTile;
	public static BufferedImage wrongFlag;
	
	public static BufferedImage tileOpen;
	public static BufferedImage tileOne;
	public static BufferedImage tileTwo;
	public static BufferedImage tileThree;
	public static BufferedImage tileFour;
	public static BufferedImage tileFive;
	public static BufferedImage tileSix;
	public static BufferedImage tileSeven;
	public static BufferedImage tileEight;
	
	public static BufferedImage tileMine;
	
	public static BufferedImage clock;

	public static void init() {
		if(tile == null) {
			tile = readImageFile("tile");
			flaggedTile = readImageFile("flagged");
			wrongFlag = readImageFile("flagged_wrong");
			tileOpen = readImageFile("tile_empty");
			tileOne = readImageFile("tile_one");
			tileTwo = readImageFile("tile_two");
			tileThree = readImageFile("tile_three");
			tileFour = readImageFile("tile_four");
			tileFive = readImageFile("tile_five");
			tileSix = readImageFile("tile_six");
			tileSeven = readImageFile("tile_seven");
			tileEight = readImageFile("tile_eight");
			tileMine = readImageFile("tile_mine");
			clock = readImageFile("clock");
		}
	}

	private static BufferedImage readImageFile(String fileName) {
		BufferedImage img = null;
		System.out.println("[ResourceManager]: Reading "+fileName+".png");
		try {
			img = ImageIO.read(new File("src/resources/"+fileName+".png"));
		} catch (IOException e) {
			System.err.println("[ResourceManager]: Error when reading src/resources/"+fileName+".png");
			System.exit(-1);
		}
		return img;
	}
}
