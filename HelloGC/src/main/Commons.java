package main;

import static org.lwjgl.opengl.GL11.*;
import io.Keyboard;
import io.Mouse;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import texture.Texture;

public class Commons {
	// ウィンドウ・描写領域
	public final static int WINDOW_WIDTH = 800;
	public final static int WINDOW_HEIGHT = 600;
	public final static int WIDTH = 400;
	public final static int HEIGHT = 300;
//	public final static int WINDOW_WIDTH = 640;
//	public final static int WINDOW_HEIGHT = 320;
//	public final static int WIDTH = 320;
//	public final static int HEIGHT = 240;
	public final static int DEPTH = 200;

	// リソースへのパス
	public final static String RESOURCE_FOLDER_STRING = "resources/";
	public final static String IMAGE_FOLDER_STRING = RESOURCE_FOLDER_STRING
			+ "images/";

	// FPS管理
	public static final int FPS = 60;
	public static long frameCount = 0;

	// IO
	public static Keyboard keyboard;
	public static Mouse mouse;

	// ゲーム利用
	public static Random rnd;

	// 文字描写
	public static Font font = null;
	public static final String FONT_FILEPATH = RESOURCE_FOLDER_STRING
			+ "Ricty.ttf";
	public static final int FONT_HEIGHT = 60;

	/**
	 * 初期化
	 */
	public static void initializeCommons() {
		rnd = new Random(System.nanoTime());
		font = createFont();
	}

	/**
	 * min から max までの間で乱数を生成して返す
	 */
	public static float random(float min, float max) {

		float dist = max - min;
		return (float) (Math.random() * dist + min);
	}

	/**
	 * Font生成
	 */
	private static Font createFont() {
		try {
			InputStream is = new FileInputStream(FONT_FILEPATH);
			Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(
					(float) FONT_HEIGHT);
			is.close();
			return font;
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * テクスチャつきポリゴンの描写
	 */
	public static void drawTexture(Texture texture, int width, int height) {
		texture.bind();

		// 四角のポリゴンとする
		glBegin(GL_QUADS);

		texture.point(texture.getWidth(), 0);
		glVertex3f(width / 2, height / 2, 0);
		texture.point(0, 0);
		glVertex3f(-width / 2, height / 2, 0);
		texture.point(0, texture.getHeight());
		glVertex3f(-width / 2, -height / 2, 0);
		texture.point(texture.getWidth(), texture.getHeight());
		glVertex3f(width / 2, -height / 2, 0);

		glEnd();
	}
}
