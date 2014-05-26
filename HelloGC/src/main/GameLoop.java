package main;

import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import glogic.GameSceneMaster;

import org.lwjgl.opengl.Display;

class GameLoop implements Runnable {
	private GameSceneMaster sceneMaster;

	public GameLoop(GameSceneMaster sceneMaster) {
		this.sceneMaster = sceneMaster;
	}

	@Override
	public void run() {
		FrameController frameController = new FrameController();
		
		while (!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			KEYBOARD.update();
			sceneMaster.update();
			sceneMaster.render();
			
			frameController.update();
		}
	}

}
