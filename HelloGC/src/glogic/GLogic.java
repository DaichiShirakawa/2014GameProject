package glogic;

import gobject.GameObject;
import gobject.TextTest;
import gobject.dottest.DotTest;
import gobject.galaxy.Star;
import gobject.weather.Flowers;

import java.util.ArrayList;

public class GLogic {
	private ArrayList<GameObject> gameObjects;

	public GLogic() {
		gameObjects = new ArrayList<>(); 
//		addObject(new Flowers());
//		addObject(new DotTest());
//		addObject(new TextTest());
		Star sun = new Star(null, "太");
		addObject(sun);
		addObject(sun.makeChild("水", 30));
		addObject(sun.makeChild("金", 60));
		Star earth = sun.makeChild("地", 90);
		addObject(earth);
		addObject(earth.makeChild("a", 15));
		addObject(sun.makeChild("火", 120));
		addObject(sun.makeChild("木", 150));
		addObject(sun.makeChild("土", 180));
		addObject(sun.makeChild("天", 210));
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
