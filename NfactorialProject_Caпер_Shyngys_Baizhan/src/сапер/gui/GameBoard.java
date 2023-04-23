package сапер.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.sound.sampled.*;
import сапер.gameelements.Grid;
import сапер.sound.SoundEffect;
import сапер.utils.Difficulty;
import сапер.utils.MapGenerator;
import сапер.utils.ResourceManager;

public class GameBoard extends JPanel implements MouseListener, ActionListener {

	private MapGenerator mapGenerator;
	private Grid grid;
	
	private Difficulty difficulty;
	private int minesOnField;
	private int timeInSeconds;
	
	private Timer timer;

	public GameBoard(Difficulty diff) {
		super();
		setFocusable(true);
		addMouseListener(this);
		mapGenerator = new MapGenerator();
		grid = new Grid(diff.getWidth(), diff.getHeight(), diff.getMinesCount());
		minesOnField = diff.getMinesCount();
		difficulty = diff;
		timeInSeconds = 0;
		timer = new Timer(1000, this);
		timer.start();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!detectHasLost() && !detectHasWon()) {
			if(e.getButton() == MouseEvent.BUTTON3) {
				if(!grid.getTile(e.getY()/36, e.getX()/36).isFlagged() && !grid.getTile(e.getY()/36, e.getX()/36).isOpen()) {
					grid.getTile(e.getY()/36, e.getX()/36).flag();
					minesOnField--;
				}
				else if(grid.getTile(e.getY()/36, e.getX()/36).isFlagged()) {
					grid.getTile(e.getY()/36, e.getX()/36).unflag();
					minesOnField++;
				}
			}
			else if(e.getButton() == MouseEvent.BUTTON1) {
				if(!grid.getTile(e.getY()/36, e.getX()/36).isFlagged())
					grid.open(e.getY()/36, e.getX()/36);
			}
		}
		else {
			DisplayManager.addMainMenu();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaint(); revalidate();
		
		//Background
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, DisplayManager.getWindowWidth(), DisplayManager.getWindowHeight());
		
		g.drawImage(ResourceManager.flaggedTile, 60, DisplayManager.getWindowHeight()-75, 36, 36, null);
		g.drawImage(ResourceManager.clock, DisplayManager.getWindowWidth()-200, DisplayManager.getWindowHeight()-75, 36, 36, null);
		
		//Draw tiles
		mapGenerator.drawGrayTiles((Graphics2D) g, grid);
		mapGenerator.drawGreenTiles((Graphics2D) g, grid);
		
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.WHITE);
		g.drawString("x"+minesOnField, 100, DisplayManager.getWindowHeight()-50);
		g.drawString(""+timeInSeconds, DisplayManager.getWindowWidth()-150, DisplayManager.getWindowHeight()-50);
		
		if(detectHasLost()) {
			Scanner scanner = new Scanner(System.in);
			File file = new File("/Users/shyngysbaizhan/IdeaProjects/untitled/src/сапер/sound/Emotional Damage.wav");
			AudioInputStream audioInputStream = null;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(file);
			} catch (UnsupportedAudioFileException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				throw new RuntimeException(e);
			}
			try {
				clip.open(audioInputStream);
			} catch (LineUnavailableException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.setColor(Color.RED);
			g.drawString("Ты проиграл!",  DisplayManager.getWindowWidth()/5, DisplayManager.getWindowHeight()/7);
			clip.start();
			String response = scanner.next();
		}
		
		if(detectHasWon()) {
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.setColor(Color.BLUE);
			g.drawString("Ты выиграл!",  DisplayManager.getWindowWidth()/5, DisplayManager.getWindowHeight()/7);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timeInSeconds++;
	}
	
	private boolean detectHasLost() {
		for(int h=0;h<grid.getHeight();h++) {
			for(int w=0;w<grid.getWidth();w++) {
				if(grid.getTile(h, w).hasMine() && grid.getTile(h, w).isOpen()) {
					timer.stop();
					for(int h1=0;h1<grid.getHeight();h1++) {
						for(int w1=0;w1<grid.getWidth();w1++) {
							if(grid.getTile(h1, w1).hasMine()) grid.getTile(h1, w1).open();
							if(grid.getTile(h1, w1).isFlagged() && !grid.getTile(h1, w1).hasMine()) grid.getTile(h1, w1).displayWrong(true);
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean detectHasWon() {
		int rightCount = 0;
		for(int h=0;h<grid.getHeight();h++) {
			for(int w=0;w<grid.getWidth();w++) {
				if(grid.getTile(h, w).hasMine() && grid.getTile(h, w).isFlagged())
					rightCount++;
			}
		}
		if(rightCount == difficulty.getMinesCount()) {
			timer.stop();
			return true;
		}
		return false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
