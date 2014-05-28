package main;

import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.scene.GameSceneManager;

import org.lwjgl.opengl.Display;

class GameLoop implements Runnable {
	private GameSceneManager sceneManager;

	public GameLoop(GameSceneManager sceneMaster) {
		this.sceneManager = sceneMaster;
	}

	@Override
	public void run() {
		FPSManager fpsManager = FPSManager.getInstance();
		
		while (!Display.isCloseRequested()) {
			initBuffer();

			KEYBOARD.update();
			sceneManager.update();
			sceneManager.render();
			
			fpsManager.update();
		}
	}

	private void initBuffer() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

}
