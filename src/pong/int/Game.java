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
	public static final int WIDTH = 600, HEIGHT = WIDTH/12 * 9; //16:9
	
	private Thread thread; //whole game in one thread
	private boolean running = false;
	
	//private Random r;//may not use
	private Handler handler; //need to make one for pong
	private HUD hud; //may not use
	//private Spawn spawner; //may not use
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler)); //ad keyboard input
		
		new Window(WIDTH, HEIGHT,"Let's Play Pong", this); //create window
		
		hud = new HUD(); //create HUD
		
		handler.addObject(new Paddle(0,HEIGHT/2-100,20,150,"p1", handler));
		handler.addObject(new Paddle(WIDTH-27,HEIGHT/2-100,20,150,"p2", handler)); //why 613?, who does right side pixel start at 633?
		handler.addObject(new Ball(WIDTH/2 - 25,50,25,25,"ball", handler));
		//spawner = new Spawn(handler, hud); //don't need, not spawning anything
		
		
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
		hud.tick();
		//spawner.tick();
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
		
		//g.setColor(Color.white); //set background black
		/*
		Paddle test = new Paddle(0,100,20,150,"p1");
		Ball test2 = new Ball(100,100,25,25,"ball");
		
		g.fillRect(test.getX(), test.getY(), test.getSizeX(), test.getSizeY());
		
		g.fillRect(test2.getX(), test2.getY(), test2.getSizeX(), test2.getSizeY());
		*/
		
		handler.render(g);
		
		hud.render(g);
		
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
