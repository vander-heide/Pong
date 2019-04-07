package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject{

	private Handler handler;
	
	public Ball(int x, int y, int sizeX, int sizeY, String id, Handler handler) {
		super(x, y, sizeX, sizeY, id);
		
		//set these to something so it moves when player starts to play
		velX = -1;
		velY = 1;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, sizeX, sizeY);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,sizeX,sizeY);
	}

}
