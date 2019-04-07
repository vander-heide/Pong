package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Paddle extends GameObject{

	private Handler handler;
	//maybe make velX static somehow??
	
	public Paddle(int x, int y, int sizeX, int sizeY, String id, Handler handler) {
		super(x, y, sizeX, sizeY, id);
		
		//this.handler = handler;
				
		velX = 0; //needs to final, not sure if i can change since ball needs this as reg int, just don't fuck with it?
		velY = 0;
	}

	@Override
	public void tick() {
		//x += velX;
		y += velY;
		
		
		x = Game.clamp(x, 0, Game.WIDTH - sizeX - 6);
		y = Game.clamp(y, 0, Game.HEIGHT - sizeY - 40);
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white); //set background black
		g.fillRect(x, y, sizeX, sizeY);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,sizeX,sizeY);
	}

	//no need to look for a collision, the ball needs to look for collisions
	
}
