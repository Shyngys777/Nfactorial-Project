package сапер.utils;

import java.awt.Graphics2D;

import сапер.gameelements.Grid;

public class MapGenerator {

	public void drawGrayTiles(Graphics2D g, Grid grid) {
		for(int h=0;h<grid.getHeight();h++) {
			for(int w=0;w<grid.getWidth();w++) {
				switch(grid.getTile(h, w).getNumber()) {
				case -1:
					g.drawImage(ResourceManager.tileMine, w*36, h*36, 36, 36, null); break;
				case 0:
					g.drawImage(ResourceManager.tileOpen, w*36, h*36, 36, 36, null); break;
				case 1:
					g.drawImage(ResourceManager.tileOne, w*36, h*36, 36, 36, null); break;
				case 2:
					g.drawImage(ResourceManager.tileTwo, w*36, h*36, 36, 36, null); break;
				case 3:
					g.drawImage(ResourceManager.tileThree, w*36, h*36, 36, 36, null); break;
				default:
					break;
				}
			}
		}
	}

	public void drawGreenTiles(Graphics2D g, Grid grid) {
		for(int h=0;h<grid.getHeight();h++) {
			for(int w=0;w<grid.getWidth();w++) {
				if(!grid.getTile(h, w).isOpen())
					g.drawImage(ResourceManager.tile, w*36, h*36, 36, 36, null);
				if(grid.getTile(h, w).isFlagged())
					g.drawImage(ResourceManager.flaggedTile, w*36, h*36, 36, 36, null);
				if(grid.getTile(h, w).isWrong())
					g.drawImage(ResourceManager.wrongFlag, w*36, h*36, 36, 36, null);
			}
		}
	}
}
