package pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.util.LinkedList;
//import java.util.Stack;

//gets keyboard input
public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private int up, down; //used to keep track what buttons are currently pressed, if mapping a lot of keys use array
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		up = 0;
		down = 0;
	}
	
	//do things if button pressed
	
	//to do left hold, press right, still holding left, use a stack?
	//maybe use this:   http://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); //store a key ascii code
		//System.out.println(key); //print it
		
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			
			
			if(tempObject.getId() == "p1") {
				/*	OG movement
				if(key == KeyEvent.VK_W) tempObject.setVelY(-5);
				if(key == KeyEvent.VK_S) tempObject.setVelY(5);
				if(key == KeyEvent.VK_D) tempObject.setVelX(5);
				if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
				*/
				
				if(key == KeyEvent.VK_UP) {
					tempObject.setVelY(-8);
					up = 1;
				}
				if(key == KeyEvent.VK_DOWN) {
					tempObject.setVelY(8);
					down = 1;
				}
			
				/*
				if (key == KeyEvent.VK_W) {
					tempObject.ay[0] = 1;
					tempObject.setVelY(-5);
				}
				if (key == KeyEvent.VK_S) {
					tempObject.ay[1] = 1;
					tempObject.setVelY(5);
				}
				if (key == KeyEvent.VK_A) {
					tempObject.ax[0] = 1;
					tempObject.setVelX(-5);
				}
				if (key == KeyEvent.VK_D) {
					tempObject.ax[1] = 1;
					tempObject.setVelX(5);
				}	
				*/
			
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		
	}
	
	//do nothing if button not pressed (makes more fluid movement)
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == "p1") {
				/*	OG movement
				if(key == KeyEvent.VK_W) tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
				*/
				
				if(key == KeyEvent.VK_UP) {
					up = 0;
					if (down == 1) {
						tempObject.setVelY(8);
					}
					else {
						tempObject.setVelY(0);
					}
					
				}
				if(key == KeyEvent.VK_DOWN) {
					down = 0;
					if (up == 1) {
						tempObject.setVelY(-8);
					}
					else {
						tempObject.setVelY(0);
					}
					
				}
				/*
				if (key == KeyEvent.VK_W) {
					tempObject.ay[0] = 0;
					if(tempObject.ay[1]==1) {
						tempObject.setVelY(5);
					}else {
						tempObject.setVelY(0);
					}
				}
				if (key == KeyEvent.VK_S) {
					tempObject.ay[1] = 0;
					if(tempObject.ay[0]==1) {
						tempObject.setVelY(-5);
					}else {
						tempObject.setVelY(0);
					}
				}
				if (key == KeyEvent.VK_A) {
					tempObject.ax[0] = 0;
					if(tempObject.ax[1]==1) {
						tempObject.setVelX(5);
					}else {
						tempObject.setVelX(0);
					}
				}
				if (key == KeyEvent.VK_D) {
					tempObject.ax[1] = 0;
					if(tempObject.ax[0]==1) {
						tempObject.setVelX(-5);
					}else {
						tempObject.setVelX(0);
					}
				}
				*/	
			}
		}
	}
	
}
