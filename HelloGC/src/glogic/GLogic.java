package glogic;

import gobject.GameObject;
import gobject.weather.Flowers;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

public class GLogic {
	private ArrayList<GameObject> gameObjects;

	public GLogic() {
		gameObjects = new ArrayList<>(); 
		gameObjects.add(new Flowers());
	}

	public void gameUpdate() {
		for(GameObject gameObject: gameObjects) {
			gameObject.update();
		}
	}

	public void gameRender() {
		for(GameObject gameObject: gameObjects) {
			gameObject.render();
		}
	}
	
	public void terminate() {
		for(GameObject gameObject:gameObjects) {
			gameObject.terminate();
		}
	}
}
