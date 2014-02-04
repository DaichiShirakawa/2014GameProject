package glogic;

import gobject.GameObject;
import gobject.weather.Flowers;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import dottest.DotTest;
import dottest.TextTest;

public class GLogic {
	private ArrayList<GameObject> gameObjects;

	public GLogic() {
		gameObjects = new ArrayList<>(); 
		addObject(new Flowers());
		addObject(new DotTest());
		addObject(new TextTest());
		
	}
	
	private GameObject addObject(GameObject gameObject) {
		gameObjects.add(gameObject);
		return gameObject;
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
