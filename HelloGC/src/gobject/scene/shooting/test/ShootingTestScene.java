package gobject.scene.shooting.test;

import gobject.GameObject;
import gobject.character.shooting.ShootingCharacter;
import gobject.character.shooting.bullets.ShootingBulletCharacter;
import gobject.character.shooting.test.EnemyShip;
import gobject.character.shooting.test.MyShip;
import gobject.scene.GameSceneImpl;
import gobject.scene.shooting.ShootingScene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import common.CommonMethod.BackGroundColor;

public final class ShootingTestScene extends GameSceneImpl implements
		ShootingScene {
	private static ShootingScene instance;

	private List<ShootingCharacter> friendlies = new ArrayList<ShootingCharacter>();
	private List<ShootingCharacter> enemies = new ArrayList<ShootingCharacter>();
	private List<ShootingBulletCharacter> bullets = new ArrayList<ShootingBulletCharacter>();
	private List<ShootingBulletCharacter> generatingBullets = new ArrayList<ShootingBulletCharacter>();

	// HACK とりあえず。getInstanceへ依存する構造をやめる必要あり
	public ShootingScene() {
		BackGroundColor.BLACK.set();
		friendlies.add((ShootingCharacter) add(new MyShip()));
		enemies.add((ShootingCharacter) add(new EnemyShip()));
		instance = this;
	}

	public static ShootingScene getInstance() {
		if (null == instance) {
			instance = new ShootingScene();
		}
		return instance;
	}

	@Override
	public void update() {
		for (Iterator<GameObject> ite = getIterator(); ite.hasNext();) {
			GameObject go = ite.next();
			go.update();
			if (go.canDispose()) {
				go.dispose();
				ite.remove();
				friendlies.remove(go);
				enemies.remove(go);
				bullets.remove(go);
			}
		}
		for (ShootingBulletCharacter bullet : generatingBullets) {
			bullets.add((ShootingBulletCharacter) add(bullet));
		}
		generatingBullets.clear();
	}

	public void shoot(ShootingBulletCharacter bullet) {
		generatingBullets.add(bullet);
	}

	public List<ShootingCharacter> getFriendries() {
		return friendlies;
	}

	public List<ShootingCharacter> getEnemies() {
		return enemies;
	}
}
