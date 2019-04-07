package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	private State state;
	private Handler handler;
	
	private int p1Score = 0;
	private int p2Score = 0;
	protected boolean draw = true;
	
	public HUD(Handler handler, State state) {
		this.state = state;
		this.handler = handler;
	}
		
	
	public void tick() {

	}
	
	
	public void render(Graphics g) {
		if (state.gameState == State.STATE.Win) {
			gameWin(g);
		}
		if (state.gameState == State.STATE.Game) {
			g.setColor(Color.white);
			//g.drawString("Score: ",  15,  64);
			int x = Game.WIDTH/2-1;
			int y = 0;
			int width = 10;
			for (int i = 0;i < Game.HEIGHT/8;i++) {
				g.fillRect(x, y, 2, width);
				y += width * 4;
			}
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
			g.drawString(String.valueOf(p1Score),  Game.WIDTH/4,  50);
			g.drawString(String.valueOf(p2Score),  3*(Game.WIDTH/4),  50);
		}
		

	}
	
	private void gameWin(Graphics g) {
		Font fnt = new Font("ariel", 1, 50);
		Font fnt2 = new Font("ariel", 1, 30);
				
		g.setFont(fnt);
		g.setColor(Color.white);
		if (p1Score > p2Score) {
			g.drawString("P1 WINS",  Game.WIDTH/2-100,  100);
		}
		else {
			g.drawString("P2 WINS",  Game.WIDTH/2-100,  100);
		}

		g.drawRect(Game.WIDTH/2-125,  Game.HEIGHT/3 * 2,  250,  50);
		g.setFont(fnt2);
		g.drawString("Menu",  Game.WIDTH/2-40, Game.HEIGHT/3 * 2+36);

	}
	
	
	public void setP1(int score) {
		p1Score = score;
	}
	
	public void setP2(int score) {
		p2Score = score;
	}
	
	public void incP1() {
		p1Score++;
	}
	
	public void incP2() {
		p2Score++;
	}
	
	public int getp1Score() {
		return p1Score;
	}
	
	public int getp2Score() {
		return p2Score;
	}

}
