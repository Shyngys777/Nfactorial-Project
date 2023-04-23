package сапер.gameelements;

import java.util.Random;

public class Grid {

	private Tile[][] tileMap;
	
	private int width;
	private int height;
	private int minesCount;

	public Grid(int width, int height, int minesCount) {
		this.tileMap = new Tile[height][width];
		this.width=width; this.height=height;
		this.minesCount=minesCount;
		
		for(int h=0;h<height;h++) {
			for(int w=0;w<width;w++) {
				tileMap[h][w] = new Tile();
			}
		}
		
		placeMines();
		setNumbers();
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Tile getTile(int h, int w) {
		return tileMap[h][w];
	}

	public void open(int h, int w) {
		tileMap[h][w].open();
		
		if(tileMap[h][w].getNumber() == 0) {
			try {
				if(tileMap[h-1][w+1].canOpen()) open(h-1, w+1);
			} catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(tileMap[h-1][w].canOpen()) open(h-1, w);
			} catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(tileMap[h-1][w-1].canOpen()) open(h-1, w-1);
			} catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(tileMap[h][w-1].canOpen()) open(h, w-1);
			} catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(tileMap[h][w+1].canOpen()) open(h, w+1);
			} catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(tileMap[h+1][w+1].canOpen()) open(h+1, w+1);
			} catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(tileMap[h+1][w].canOpen()) open(h+1, w);
			} catch(ArrayIndexOutOfBoundsException e) {}
			try {
				if(tileMap[h+1][w-1].canOpen()) open(h+1, w-1);
			} catch(ArrayIndexOutOfBoundsException e) {}
		}
	}

	public void revealAllMines() {
		for(int h=0;h<height;h++) {
			for(int w=0;w<width;w++) {
				if(tileMap[h][w].hasMine() && !tileMap[h][w].isFlagged())
					tileMap[h][w].open();
				else if(tileMap[h][w].isFlagged() && !tileMap[h][w].hasMine())
					tileMap[h][w].displayWrong(true);
			}
		}
	}

	private void placeMines() {
		Random rand = new Random();
		for(int i=0;i<minesCount;i++) {
			int h = rand.nextInt(height);
			int w = rand.nextInt(width);
			
			if(tileMap[h][w].hasMine()) i--;
			else tileMap[h][w].setNumber(-1);
		}
	}

	private void setNumbers() {
		int mineCount = 0;
		for(int h=0;h<height;h++) {
			for(int w=0;w<width;w++) {
				if(tileMap[h][w].getNumber() == 0) {
					for(int h1=h-1;h1<=h+1;h1++) {
						for(int w1=w-1;w1<=w+1;w1++) {
							try {
								if(tileMap[h1][w1].hasMine()) {
									mineCount++;
								}
							} catch(ArrayIndexOutOfBoundsException e) {
								continue;
							}
						}
					}
					tileMap[h][w].setNumber(mineCount);
					mineCount = 0;
				}
			}
		}
	}
}
