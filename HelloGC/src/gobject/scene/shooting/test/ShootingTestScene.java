package gobject.scene.shooting.test;

import gobject.character.shooting.test.EnemyShip;
import gobject.character.shooting.test.MyShip;
import gobject.scene.shooting.ShootingScene;

import common.CommonMethod.BackGroundColor;

public final class ShootingTestScene extends ShootingScene {

	public ShootingTestScene() {
		BackGroundColor.BLACK.set();
		add(new MyShip(this));
		add(new EnemyShip(this));
	}
}
