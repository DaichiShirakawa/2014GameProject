package gobject.scene.shooting.edf;

import gobject.character.shooting.ShootingCharacter;
import gobject.character.shooting.bullets.ShootingBulletCharacter;
import gobject.character.shooting.edf.EDFShip;
import gobject.character.shooting.edf.Earth;
import gobject.scene.shooting.ShootingScene;

import common.CommonMethod.BackGroundColor;

public class EDFScene extends ShootingScene {
	public EDFScene() {
		BackGroundColor.BLACK.set();

		add(new Earth(this));
		add(new EDFShip(this));
	}

	@Override
	public void shoot(ShootingCharacter shooter, ShootingBulletCharacter bullet) {
		add(bullet);
	}
}
