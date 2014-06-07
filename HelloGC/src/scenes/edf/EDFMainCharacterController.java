package scenes.edf;

import classes.character.shooting.ShootingWeaponCharacter;
import classes.scene.ShootingScene;

import common.CommonMethod.BackGroundColor;

public class EDFMainCharacterController extends ShootingScene{
	EDFEarth earth;
	EDFShip ship;

	public EDFMainCharacterController() {
		BackGroundColor.BLACK.set();

		earth = add(new EDFEarth(this));
		ship = add(new EDFShip(this));
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
