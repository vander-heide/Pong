package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends GameObject{

	private Handler handler;
	private HUD hud;
	private State state;
	
	
	//not using
	private double[] normals = {13.13,9.46,7.59,3.81,1.91}; //hard code for now, lel
	private int[] paddle1 = {0,0,0};
	private int[] paddle2 = {0,0,0};
	
	public Ball(double x, double y, double sizeX, double sizeY, String id, Handler handler, HUD hud, State state) {
		super(x, y, sizeX, sizeY, id);
		
		//set these to something so it moves when player starts to play
		this.handler = handler;
		this.hud = hud;
		this.state = state;
		velX = -5;
		velY = 5;
	}

	@Override
	public void tick() {
		
		x += velX;
		y += velY;
		
		y = Game.clamp((int)y, 0, Game.HEIGHT - (int)sizeY - 40); 
		
		if (y == Game.HEIGHT - sizeY - 40 || y == 0) {
			velY *= -1; //if at top or bottom of screen
			//lines();
		}
		
		collision();
		score();
	}
	
	private void reset() {
		//handler.addObject(new Ball(Game.WIDTH/2 - 25,200,25,25,"b1", handler, hud));
		x = Game.WIDTH/2 - 25;
		y = 200;

		Random rand = new Random();

		// Obtain a number between [0 - 49].
		int n = rand.nextInt(1);
		if (n == 0) {
			n -= 1;
		}
		velX = -5;
		velY = 5*n;
		
	}
	
	private void score() {
		GameObject temp = handler.object.get(0);
		if (x < temp.getSizeX()) {
			hud.incP2();
			reset();
			//System.out.println(hud.getp1Score());
		}
		if (x+sizeX > Game.WIDTH - temp.getSizeX()) {
			hud.incP1();
			reset();
			//System.out.println(hud.getp2Score());
		}
		if (hud.getp1Score() == 10 || hud.getp2Score() == 10) {
			state.gameState = State.STATE.Win;
		}
	}
	
	private void lines() {
		int m = (int) (velY/velX);
		int b = (int) ((y+(sizeY/2)) - m*(x+(sizeX/2)));
		//left paddle
		Rectangle pad1 = handler.object.get(0).getBounds();
		//top
		paddle1[0] = (pad1.y - b) / m; //find x
		paddle1[1] = m*pad1.x + b; //find y
		paddle1[2] = ((pad1.y+pad1.height) - b) / m; //find x
		
		//right paddle
		Rectangle pad2 = handler.object.get(1).getBounds();
		paddle2[0] = (pad2.y - b) / m; //find x
		paddle2[1] = m*pad2.x + b; //find y
		paddle2[2] = ((pad2.y+pad2.height) - b) / m; //find x
	}

	//iff proper collision, return new speed ([x,y]) given a side of the padddle (0 for left (closer to y = 0), 1 for right (closer to y = max(y)))
	private double[] deflection(String paddle) {
		
		//maybe just hard code this to certain angles depending on how far down the paddle it is?
		
		//0 is p1, 1 is p2, 2 is ball
		double px = -1;
		double py = -1;
		String pi = "gg";
		double[] rspeed = {(velY*-1), velY};
		if (paddle == "p1") {
			GameObject temp = handler.object.get(0);
			px = temp.getX();
			py = temp.getY();
			pi = temp.getId();
		}
		
		if (paddle == "p2") {
			GameObject temp = handler.object.get(1);
			px = temp.getX();
			py = temp.getY();
			pi = temp.getId();
		}

		double skew = 0;
		double A = -1;
		if (py <= y && y < (py+55)) { //look at notes for paddle bounds
			skew = (x - py) / ((py+55) - py); //denom is just 60?
			A = 60 * skew;

			
		} else if ((py+95) < y && y <= (py+150)) {
			skew = ((py+150) - x) / ((py+150) - (py+95));//denom just 150-95
			A = -60 * skew;
			//System.out.println("bottom " + skew + ", " + A);
			
		} else {
			//System.out.println("notin");
			return rspeed;
		}
		
		//not sure if swap sin or cos
		double nx = Math.sin(Math.toRadians(A));
		double ny = Math.cos(Math.toRadians(A));
		double dot = velX*nx + velY*ny;//a1*a2+b1*b2
		//cosine law?
		rspeed[0] = velX - 2*dot*nx;
		rspeed[1] = velY - 2*dot*ny;
		if (pi == "p1") {
			if (rspeed[0] < 0) rspeed[0] *= -1;
		}
		if (pi == "p2") {
			if (rspeed[0] > 0) rspeed[0] *= -1;
		}
		
		return rspeed;

	}

	@Override
	public void render(Graphics g) {
		if (state.gameState == State.STATE.Game) {
			g.setColor(Color.red);
			g.fillRect((int) x, (int) y, (int) sizeX, (int) sizeY);
		}
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x-1,(int)y-1,(int)sizeX+1,(int)sizeY+1);
	}
	
	private void collision() {
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == "p1") {
				 
				//front of paddle
				if (x <= (tempObject.getX() + tempObject.getSizeX())) {
					if (y + sizeY >= tempObject.getY() && y < tempObject.getY() + tempObject.getSizeY()) {
						velX*= -1;
					}
				}
			}	
			if (tempObject.getId() == "p2") {
				if (x >= tempObject.getX()-sizeX) {
					if (y + sizeY >= tempObject.getY() && y < tempObject.getY() + tempObject.getSizeY()) {
						velX*= -1;
					}
				}	
			}				
		}
	}
}


