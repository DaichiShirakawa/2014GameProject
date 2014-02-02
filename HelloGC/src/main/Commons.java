package main;

import io.Keyboard;
import io.Mouse;

import java.util.Random;

public class Commons {
	//ウィンドウ・描写領域
	public final static int WINDOW_WIDTH = 640;
	public final static int WINDOW_HEIGHT = 480;
	public final static int WIDTH = 320;
	public final static int HEIGHT = 240;
	public final static int DEPTH = 200;
	
	//リソースへのパス
	public final static String IMAGE_FOLDER_STRING = "images/";
	
	// FPS管理
	public static final int FPS = 60;
	public static long frameCount = 0;
	
	//IO
	public static Keyboard keyboard;
	public static Mouse mouse;
	
	//ゲーム利用
	public static Random rnd;
	
	/**
	 * 初期化
	 */
	public static void initializeCommons() {
		Commons.rnd = new Random(System.nanoTime());
	}
	
    public static float random(float min, float max) {
        //  min から max までの間で乱数を生成して返す
        float   dist = max - min;
        return (float)(Math.random() * dist + min);
    }
	
}
