package scenes.edf;

import scenes.edf.weapons.BasicWeapon;
import classes.character.shooting.ShootingWeaponCharacter;
import classes.scene.ShootingScene;

import common.CommonMethod.BackGroundColor;
import common.LR;

public class EDFMainCharacterController extends ShootingScene{
	EDFEarth earth;
	EDFShip ship;

	public EDFMainCharacterController() {
		BackGroundColor.BLACK.set();

		earth = add(new EDFEarth(this));

		ship = add(new EDFShip(this));
		ship.equipLeft(new BasicWeapon(this, ship, LR.LEFT));
		ship.equipRight(new BasicWeapon(this, ship, LR.RIGHT));
	}

	public ShootingWeaponCharacter getRightWeapon() {
		return ship.getRightWeapon();
	}

	public ShootingWeaponCharacter getLeftWeapon() {
		return ship.getLeftWeapon();
	}

	public boolean earthArrive() {
		return !earth.zeroHP();
	}
}
