package pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.util.LinkedList;
//import java.util.Stack;

//gets keyboard input
public class KeyInput extends KeyAdapter{
	
	private State state;
	private Handler handler;
	private int up, down, pause; //used to keep track what buttons are currently pressed, if mapping a lot of keys use array
	
	private double[][] speed;
	
	public KeyInput(Handler handler, State state) {
		this.state = state;
		this.handler = handler;
		up = 0;
		down = 0;
		pause = -1;
		speed = new double[3][2];
	}
	
	//do things if button pressed
	
	//to do left hold, press right, still holding left, use a stack?
	//maybe use this:   http://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); //store a key ascii code
		//System.out.println(key); //print it
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		if(key == KeyEvent.VK_ENTER) { //pause game
			pause *= -1;
			if (state.gameState == State.STATE.Game) {
				if (pause > 0) { //if positive, game should be paused, stop everything
					for (int i=0; i<handler.object.size();i++) { //loop through objects
						GameObject tempObject = handler.object.get(i);
						speed[i][0] = tempObject.getVelX();
						speed[i][1] = tempObject.getVelY();
						tempObject.setVelX(0);
						tempObject.setVelY(0);
					}
				}
				if (pause < 0) { //if negative, should be running
					for (int i=0; i<handler.object.size();i++) { //loop through objects
						GameObject tempObject = handler.object.get(i);
						tempObject.setVelX(speed[i][0]);
						tempObject.setVelY(speed[i][1]);
					}
				}
			}
		}
		
		
		if (state.gameState == State.STATE.Game) {
		
			for(int i=0;i<handler.object.size();i++) {
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == "p1") {
					if(key == KeyEvent.VK_UP) {
						tempObject.setVelY(-8);
						up = 1;
					}
					if(key == KeyEvent.VK_DOWN) {
						tempObject.setVelY(8);
						down = 1;
					}
				}
			}
		}
		
		
	}
	
	//do nothing if button not pressed (makes more fluid movement)
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (state.gameState == State.STATE.Game) {
		
			for(int i=0;i<handler.object.size();i++) {
				GameObject tempObject = handler.object.get(i);
			
				if(tempObject.getId() == "p1") {
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
				}
			}
		}
	}
	
}
