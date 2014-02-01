package main;

import java.util.Random;

import io.Keyboard;
import io.Mouse;

import javax.swing.JPanel;

public class Commons {
	public final static int WINDOW_WIDTH = 640;
	public final static int WINDOW_HEIGHT = 480;
	public final static int WIDTH = 320;
	public final static int HEIGHT = 240;
	public final static int DEPTH = 100;
	
	public static JPanel mainPanel;
	public static Mouse mouse;
	public static Keyboard keyboard;
	public static Random rnd;
	
	public static void initializeCommons(JPanel panel, Mouse mouse, Keyboard keyboard) {
		Commons.mainPanel = panel;
		Commons.mouse = mouse;
		Commons.keyboard = keyboard;
		Commons.rnd = new Random(System.nanoTime());
	}
	
	public static long frameCount = 0;
}
