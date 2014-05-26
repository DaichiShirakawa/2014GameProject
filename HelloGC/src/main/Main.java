package main;

import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import glogic.GameSceneMaster;

import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * 転職活動作品用
 * 
 * @author tokiha_s
 * @since 2014/05/26
 */
public final class Main{

	public static void main(final String[] args) {
		initialize();
		
		GameSceneMaster sceneMaster = new GameSceneMaster();
		try {
			new GameLoop(sceneMaster).run();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sceneMaster.dispose();
			Display.destroy();
			System.exit(0);
		}
	}

	private static void initialize() {
		// LWJGLネイティブライブラリの設定
		System.setProperty("org.lwjgl.librarypath", new File(
				WINDOWS_NATIVE_FOLDER_STRING).getAbsolutePath());
		createDisplay();
		initializeOpenGLSettings();
	}

	private static void createDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
			Display.setTitle(PRODUCT_TITLE);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	private static void initializeOpenGLSettings() {
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, 0, HEIGHT, -DEPTH / 2, DEPTH / 2);
		glMatrixMode(GL_MODELVIEW);
	}
}
