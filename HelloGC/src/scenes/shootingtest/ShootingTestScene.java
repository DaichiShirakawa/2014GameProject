package scenes.shootingtest;

import classes.scene.ShootingScene;
import common.CommonMethod.BackGroundColor;

public final class ShootingTestScene extends ShootingScene {

	public ShootingTestScene() {
		BackGroundColor.BLACK.set();
		add(new MyShip(this));
		add(new TestEnemyShip(this));
	}
}
