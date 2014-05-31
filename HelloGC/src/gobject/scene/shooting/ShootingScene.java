package gobject.scene.shooting;

import gobject.GameObject;
import gobject.character.bullet.GStgBullet;
import gobject.character.spaceship.EnemyShip;
import gobject.character.spaceship.GStgCharacter;
import gobject.character.spaceship.MyShip;
import gobject.scene.GameSceneImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import common.CommonMethod.BackGroundColor;

public final class ShootingScene extends GameSceneImpl {
	private static ShootingScene instance;

	
	private List<GStgCharacter> friendlies = new ArrayList<GStgCharacter>();
	private List<GStgCharacter> enemies = new ArrayList<GStgCharacter>();
	private List<GStgBullet> bullets = new ArrayList<GStgBullet>();
	private List<GStgBullet> generatingBullets = new ArrayList<GStgBullet>();

	//HACK とりあえず。getInstanceへ依存する構造をやめる必要あり
	public ShootingScene() {
		BackGroundColor.BLACK.set();
		friendlies.add((GStgCharacter) add(new MyShip()));
		enemies.add((GStgCharacter) add(new EnemyShip()));
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
		for (GStgBullet bullet : generatingBullets) {
			bullets.add((GStgBullet) add(bullet));
		}
		generatingBullets.clear();
	}

	public void shoot(GStgBullet bullet) {
		generatingBullets.add(bullet);
	}

	public List<GStgCharacter> getFriendries() {
		return friendlies;
	}

	public List<GStgCharacter> getEnemies() {
		return enemies;
	}
}
