package pong;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x, y;
	protected int sizeX, sizeY;
	protected String id;
	protected int velX, velY;
	
	//protected int ax;
	//protected int ay;
	
	public GameObject(int x, int y, int sizeX, int sizeY, String id) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		//this.ax = 0;
		//this.ay = 0;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public void setSizeX(int size) {
		this.sizeX = size;
	}
	
	public void setSizeY(int size) {
		this.sizeY = size;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getId() {
		return id;
	}

	public int getVelX(){
		return velX;
	}
	
	public int getVelY() {
		return velY;
	}
	
	
}
