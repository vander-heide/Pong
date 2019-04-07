package pong;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	private int pScore = 0;
	private int cScore = 0;
	
	public void tick() {
		
		//HEALTH = Game.clamp(HEALTH, 0, 100); //don't go past health bounds
		//greenValue = Game.clamp(greenValue, 0, 255);
		//greenValue = HEALTH*2;
		
		//score++;
	}
	
	
	public void render(Graphics g) {
		/*
		 set colour to white
		 draw dividing dotted line down centre
		 draw score
		 
		 g.setColor(Color.white);
		 
		 */
		
		/*
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		
		g.setColor(new Color(75, greenValue, 0)); //colour change from green to red, constant red, no blue, vary green
		g.fillRect(15, 15, HEALTH * 2, 32);
		
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32); //draw border around
		
		g.drawString("Score: " + score,  15,  64);
		g.drawString("Level: " + level,  15,  80);
		*/
	}
	
	
	public void setP(int score) {
		pScore = score;
	}
	
	public void setC(int score) {
		cScore = score;
	}
	
	public void incP() {
		pScore++;
	}
	
	public void incC() {
		cScore++;
	}
	
	public int getpScore() {
		return pScore;
	}
	
	public int getcScore() {
		return cScore;
	}

}
