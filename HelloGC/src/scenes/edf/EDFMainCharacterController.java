package scenes.edf;

import classes.character.shooting.EDFWeaponCharacter;
import classes.scene.ShootingScene;

import common.CommonMethod.BackGroundColor;

public class EDFMainCharacterController extends ShootingScene {
	EDFScene scene;
	EDFEarth earth;
	EDFShip ship;

	public EDFMainCharacterController(EDFScene scene) {
		BackGroundColor.BLACK.set();
		this.scene = scene;
		this.earth = add(new EDFEarth(scene));
		this.ship = add(new EDFShip(scene));
	}

	@Override
	public boolean updateProcess() {
		return super.updateProcess();
	}

	public EDFWeaponCharacter getRightWeapon() {
		return ship.getRightWeapon();
	}

	public EDFWeaponCharacter getLeftWeapon() {
		return ship.getLeftWeapon();
	}

	public boolean earthArrive() {
		return !earth.zeroHP();
	}
}
