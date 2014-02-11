package gobject.hello.shooting;

import gobject.GLogicObject;
import gobject.GameObject;
import gobject.hello.shooting.bullet.GSBulletObject;
import gobject.hello.shooting.bullet.NormalBullet;

public class ShootingLogic extends GLogicObject {
	private static ShootingLogic instance;

	private ShootingLogic() {
		addChild(new MyShip());
	}

	public static ShootingLogic GetInstance() {
		if (instance == null) {
			instance = new ShootingLogic();
		}
		return instance;
	}

	public void shoot(GSBulletObject bullet) {
		addChild(bullet);
	}

	@Override
	public void update() {
		for (int i = 0; i < children_.size(); i++) {
			GameObject go = children_.get(i);
			go.update();
			if (go.canDispose()) {
				disposeChild(go);
				i--;
			}
		}
	}

	@Override
	public void render() {
		for (GameObject go : children_) {
			go.render();
		}
	}

	@Override
	public void terminate() {
		for (GameObject go : children_) {
			go.terminate();
		}
	}
}
