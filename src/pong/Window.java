package pong;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{ //not sure what canvas is

	/**
	 * 
	 */
	private static final long serialVersionUID = 8110757548692657034L;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title); //frame of the window
		
		frame.setPreferredSize(new Dimension(width, height)); 
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //the X button to actually work
		frame.setResizable(false); //can we resize window
		frame.setLocationRelativeTo(null); //start in middle of screen
		frame.add(game); //add game to the frame
		frame.setVisible(true);
		game.start();
		
	}
}
	
	