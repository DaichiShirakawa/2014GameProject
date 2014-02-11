package glogic;

import static common.Commons.*;
import gobject.GLogicObject;
import gobject.GameObject;
import gobject.hello.shooting.ShootingLogic;

public class GLogic extends GLogicObject {

	public GLogic() {
		addChild(keyboard);
		// addObject(new Flowers());
		// addObject(new DotTest());
		// addObject(new TextTest());
		// addObject(new SolarSystem());
		addChild(ShootingLogic.GetInstance());
	}

	@Override
	public void update() {
		for (GameObject gameObject : children_) {
			gameObject.update();
		}
	}

	@Override
	public void render() {
		for (GameObject gameObject : children_) {
			gameObject.render();
		}
	}

	public void terminate() {
		for (GameObject gameObject : children_) {
			gameObject.terminate();
		}
	}
}
