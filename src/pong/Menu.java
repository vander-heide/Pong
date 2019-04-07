package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Menu extends MouseAdapter{

	private State state;
	private Handler handler;
	private HUD hud;
	
	public Menu(Handler handler, State state, HUD hud) {
		this.state = state;
		this.handler = handler;
		this.hud = hud;
	}
	
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//have if state menu in case you want to add different screens realted to different states
		
		if (state.gameState == State.STATE.Menu) {
			if (mouseOver(mx, my, Game.WIDTH/2-125, Game.HEIGHT/3, 250, 50)) {
				state.gameState = State.STATE.Game;
				handler.addObject(new Paddle(0,Game.HEIGHT/2-100,20,150,"p1", handler, state));
				handler.addObject(new Paddle(Game.WIDTH-26,Game.HEIGHT/2-100,20,150,"p2", handler, state)); //why 613?, who does right side pixel start at 633?
				handler.addObject(new Ball(Game.WIDTH/2 - 25,200,25,25,"b1", handler, hud, state));
			}
			//rerererererrerererrerereremix
			if (mouseOver(mx, my, Game.WIDTH/2-125, (int)(Game.HEIGHT/3*1.5), 250, 50)) {
				state.gameState = State.STATE.Game;
				handler.addObject(new Paddle(0,Game.HEIGHT/2-100,20,150,"p1", handler, state));
				handler.addObject(new Paddle(Game.WIDTH-26,Game.HEIGHT/2-100,20,150,"p2", handler, state)); //why 613?, who does right side pixel start at 633?
				handler.addObject(new Ball(Game.WIDTH/2 - 25,200,25,25,"b1", handler, hud, state));
			}
			if (mouseOver(mx, my, Game.WIDTH/2-125, Game.HEIGHT/3 * 2, 250, 50)) {
				System.exit(1);
			}
		}
		if (state.gameState == State.STATE.Win) {
			if (mouseOver(mx, my, Game.WIDTH/2-125, Game.HEIGHT/3 * 2, 250, 50)) {
				state.gameState = State.STATE.Menu;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y & my < y + height) {
				return true;
			} else return false;
		} else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		//maybe split this up, with an if statement that gets state, then depending on state "render" different stuff, use a switch statement
		if (state.gameState == State.STATE.Menu) {
			mainScreen(g);
		}
		if (state.gameState == State.STATE.Select) {
			gameSelect(g);
		}
		
	}
	
	
	
	private void gameSelect(Graphics g) {
		Font fnt = new Font("ariel", 1, 50);
		Font fnt2 = new Font("ariel", 1, 30);
				
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Choose Players",  Game.WIDTH/2-190,  75);
		g.drawRect(Game.WIDTH/2-125,  Game.HEIGHT/3,  250,  50);
		g.drawRect(Game.WIDTH/2-125,  (int)(Game.HEIGHT/3*1.5),  250,  50);
		g.drawRect(Game.WIDTH/2-125,  Game.HEIGHT/3 * 2,  250,  50);
		g.setFont(fnt2);
		g.drawString("1 Player",  Game.WIDTH/2-60,  Game.HEIGHT/3 + 36);
		g.drawString("2 Player",  Game.WIDTH/2-60, (int)(Game.HEIGHT/3*1.5)+36);
		g.drawString("Help",  Game.WIDTH/2-30, Game.HEIGHT/3 * 2+36);
	}
	
	private void mainScreen(Graphics g) {
		Font fnt = new Font("ariel", 1, 50);
		Font fnt2 = new Font("ariel", 1, 30);
				
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("PONG",  Game.WIDTH/2-75,  75);
		g.drawRect(Game.WIDTH/2-125,  Game.HEIGHT/3,  250,  50);
		//g.drawRect(Game.WIDTH/2-125,  (int)(Game.HEIGHT/3*1.5),  250,  50);
		g.drawRect(Game.WIDTH/2-125,  Game.HEIGHT/3 * 2,  250,  50);
		g.setFont(fnt2);
		g.drawString("START",  Game.WIDTH/2-50,  Game.HEIGHT/3 + 36);
		//g.drawString("REREREREMIX",  Game.WIDTH/2-110, (int)(Game.HEIGHT/3*1.5)+36);
		g.drawString("CLOSE",  Game.WIDTH/2-50, Game.HEIGHT/3 * 2+36);
	}
}
