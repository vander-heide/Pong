package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
//import java.util.Random;
//import java.util.Random;

public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5473171159509274509L;
	public static final int WIDTH = 600, HEIGHT = WIDTH/12 * 9; //16:9 height 450
	
	private Thread thread; //whole game in one thread
	private boolean running = false;
	
	//private Random r;//may not use
	private Handler handler; //need to make one for pong
	private HUD hud; //may not use
	//private Spawn spawner; //may not use
	private Menu menu;
	private State state;
	
	public Game() {
		handler = new Handler();
		state = new State();
		hud = new HUD(handler, state); //create HUD
		
		state.gameState = State.STATE.Menu;
		menu = new Menu(handler, state, hud);
		
		this.addKeyListener(new KeyInput(handler, state)); //ad keyboard input
		this.addMouseListener(menu);
		
		new Window(WIDTH, HEIGHT,"Let's Play Pong", this); //create window		
	}
	
	public synchronized void start() {//start up the thread
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {//stop the thread
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace(); //prints the stack to tell us what went wrong
		}
	}
	
	public void run() { //game loop, loop while running?
		this.requestFocus(); //puts game to the focus of the screen, don't have to click
		long lastTime = System.nanoTime(); // get current time to the nanosecond
		double amountOfTicks = 60.0; // set the number of ticks 
		double ns = 1000000000 / amountOfTicks; // this determines how many times we can devide 60 into 1e9 of nano seconds or about 1 second
		double delta = 0; // change in time (delta always means a change like delta v is change in velocity)
		long timer = System.currentTimeMillis(); // get current time
		//int frames = 0; // set frame variable
		while(running){ 
			long now = System.nanoTime(); // get current time in nonoseconds durring current loop
			delta += (now - lastTime) / ns; // add the amount of change since the last loop
			lastTime = now;  // set lastTime to now to prepare for next loop
			while(delta >= 1){
				// one tick of time has passed in the game this 
			    //ensures that we have a steady pause in our game loop 
			    //so we don't have a game that runs way too fast 
			    //basically we are waiting for  enough time to pass so we 
			    // have about 60 frames per one second interval determined to the nanosecond (accuracy)
			    // once this pause is done we render the next frame
			    tick();  
			    delta--;  // lower our delta back to 0 to start our next frame wait
			   }
			   if(running)//{
				   render(); // render the visuals of the game
			   //}
			   //frames++; // note that a frame has passed
			   
			   if(System.currentTimeMillis() - timer > 1000 ){ // if one second has passed
			    timer+= 1000; // add a thousand to our timer for next time
			    //System.out.println("FPS: " + frames); // print out how many frames have happend in the last second
			    //frames = 0; // reset the frame count for the next second
			   }
		}
		stop(); // no longer running stop the thread
	}
	
	private void tick() {
		handler.tick();
		
		if (state.gameState == State.STATE.Game) {
			hud.tick();
			//don't think i need anything else here?
		}else if (state.gameState == State.STATE.Menu) {
			menu.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3); //how many buffers to create,3 is best???? essentially 2 buffers that push data to screen
			return;
		}
		
		Graphics g = bs.getDrawGraphics(); //set the screen buffer???
		
		g.setColor(Color.black); //set background black
		g.fillRect(0, 0, WIDTH, HEIGHT);

		
		handler.render(g);
		
		if (state.gameState == State.STATE.Game) {
			hud.render(g);
		} else if (state.gameState == State.STATE.Win) {
			hud.render(g);
		} else if (state.gameState == State.STATE.Menu) {
			menu.render(g);
		} else if (state.gameState == State.STATE.Select) {
			menu.render(g);
		} 
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max) return var = max;
		else if(var <= min) return var = min;
		else return var;
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
