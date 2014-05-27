package main;

import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.scene.GameSceneManager;

import org.lwjgl.opengl.Display;

class GameLoop implements Runnable {
	private GameSceneManager sceneMaster;

	public GameLoop(GameSceneManager sceneMaster) {
		this.sceneMaster = sceneMaster;
	}

	@Override
	public void run() {
		FPSManager frameController = FPSManager.getInstance();
		
		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			KEYBOARD.update();
			sceneMaster.update();
			sceneMaster.render();
			
			frameController.update();
		}
	}

}
