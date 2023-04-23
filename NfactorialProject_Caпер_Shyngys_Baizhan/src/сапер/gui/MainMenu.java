package сапер.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import сапер.utils.Difficulty;

public class MainMenu extends JPanel implements MouseListener {

	private Difficulty difficulty = Difficulty.NORMAL;
	
	public MainMenu() {
		super();
		setFocusable(true);
		addMouseListener(this);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(e.getX()>100 && e.getX()<200 && e.getY()>120 && e.getY()<150) {
				toggleDifficulty();
			}
			else if(e.getX()>350 && e.getX()<450 && e.getY()>120 && e.getY()<150) {
				DisplayManager.addGameBoard(difficulty);
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaint(); revalidate();
		
		//Background
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, DisplayManager.getWindowWidth(), DisplayManager.getWindowHeight());
		
		//Title
		g.setFont(new Font("Showcard Gothic", Font.BOLD, 40));
		g.setColor(Color.WHITE);
		g.drawString("Игра Сапер", 120, 60);
		
		g.fillRoundRect(100, 120, 100, 30, 5, 5);
		g.fillRoundRect(350, 120, 100, 30, 5, 5);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Sans Serif", Font.BOLD, 14));
		g.drawString(difficulty.getName(), 110, 140);
		g.drawString("Начать игру", 360, 140);
	}
	
	/**Changes from easy to normal to hard*/
	private void toggleDifficulty() {
		if(difficulty == Difficulty.EASY) difficulty = Difficulty.NORMAL;
		else if(difficulty == Difficulty.NORMAL) difficulty = Difficulty.HARD;
		else if(difficulty == Difficulty.HARD) difficulty = Difficulty.EASY;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
