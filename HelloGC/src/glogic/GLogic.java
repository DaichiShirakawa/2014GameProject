package glogic;

import static common.Commons.*;
import gobject.GameObject;
import gobject.galaxy.SolarSystem;
import gobject.weather.Flowers;

import java.util.ArrayList;


public class GLogic {
	private ArrayList<GameObject> gameObjects;

	public GLogic() {
		gameObjects = new ArrayList<>();
		gameObjects.add(keyboard);
//		addObject(new Flowers());
//		addObject(new DotTest());
//		addObject(new TextTest());
		addObject(new SolarSystem());
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
