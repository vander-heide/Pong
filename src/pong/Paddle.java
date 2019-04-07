package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Paddle extends GameObject{

	private Handler handler;
	private State state;
	//maybe make velX static somehow??
	
	public Paddle(double x, double y, double sizeX, double sizeY, String id, Handler handler, State state) {
		super(x, y, sizeX, sizeY, id);
		
		this.handler = handler;
		this.state = state;
				
		velX = 0; //needs to final, not sure if i can change since ball needs this as reg int, just don't fuck with it?
		velY = 0;
	}

	@Override
	public void tick() {
		
		if (id == "p2") {
			x += velX;
			y += velY;
		
			x = Game.clamp((int)x, 0, (int)(Game.WIDTH - sizeX - 1)); //minus 1? OG 6
			y = Game.clamp((int)y, 0, (int)(Game.HEIGHT - sizeY - 40));
		
			//ball will always be third index, unless stuff changes. if it does just uncomment other part
			velY = handler.object.get(2).getVelY();
			/*
			String temp = ballSpeed();
			if (temp != "nope") {
				velY =  Double.parseDouble(temp);
			}
			*/
		}
		if (id == "p1") {
			y += velY;
			
			//x clamp not needed? never moves in x
			x = Game.clamp((int)x, 0, (int)(Game.WIDTH - sizeX - 1)); //minus 1? OG 6
			y = Game.clamp((int)y, 0, (int)(Game.HEIGHT - sizeY - 40));
		}
		
		
		/*
		y += velY;
		
		//x clamp not needed? never moves in x
		x = Game.clamp((int)x, 0, (int)(Game.WIDTH - sizeX - 1)); //minus 1? OG 6
		y = Game.clamp((int)y, 0, (int)(Game.HEIGHT - sizeY - 40));//this only checks max x or y value, not the whole thing relative to screen
		*/
	}
	
	private String ballSpeed() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == "b1") {
				return Double.toString(tempObject.getVelY());
			}
		}
		return "nope";
	}

	@Override
	public void render(Graphics g) {
		if (state.gameState == State.STATE.Game) {
			g.setColor(Color.white); //set background black
			g.fillRect((int)x, (int)y, (int)sizeX, (int)sizeY);
		}
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,(int)sizeX,(int)sizeY);
	}

	//no need to look for a collision, the ball needs to look for collisions
	
}
