package pong;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected double x, y;
	protected double sizeX, sizeY;
	protected String id;
	protected double velX, velY;
	
	//protected int ax;
	//protected int ay;
	
	public GameObject(double x, double y, double sizeX, double sizeY, String id) {
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
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public void setSizeX(double size) {
		this.sizeX = size;
	}
	
	public void setSizeY(double size) {
		this.sizeY = size;
	}
	
	public double getSizeX() {
		return sizeX;
	}
	
	public double getSizeY() {
		return sizeY;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public String getId() {
		return id;
	}

	public double getVelX(){
		return velX;
	}
	
	public double getVelY() {
		return velY;
	}
	
	
}
