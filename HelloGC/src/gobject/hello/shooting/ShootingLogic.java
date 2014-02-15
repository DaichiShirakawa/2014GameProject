package gobject.hello.shooting;

import gobject.GameObject;
import gobject.GameUnitManager;
import gobject.hello.shooting.bullet.GStgBullet;

import java.util.ArrayList;
import java.util.List;

public class ShootingLogic extends GameUnitManager {
	private static ShootingLogic instance_;
	private List<GStgCharacter> friendlies_;
	private List<GStgCharacter> enemies_;
	private List<GStgCharacter> bullets_;

	@SuppressWarnings("unchecked")
	private ShootingLogic() {
		friendlies_ = addControlList(new ArrayList<GStgCharacter>());
		enemies_ = addControlList(new ArrayList<GStgCharacter>());
		bullets_ = addControlList(new ArrayList<GStgBullet>());
		friendlies_.add(new MyShip());
		enemies_.add(new EnemyShip());
	}

	public static ShootingLogic GetInstance() {
		if (instance_ == null) {
			instance_ = new ShootingLogic();
		}
		return instance_;
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
	public void dispose() {
		for (GameObject go : children_) {
			go.dispose();
		}
	}

	public GStgCharacter checkHit(GStgCharacter me) {
		for (GameObject go : children_) {
			if (me.checkHitWith((GStgCharacter) go))
				return (GStgCharacter) go;
		}
		return null;
	}
}