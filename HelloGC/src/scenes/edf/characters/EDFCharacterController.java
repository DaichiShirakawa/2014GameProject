package scenes.edf.characters;

import classes.scene.ShootingScene;
import scenes.edf.EDFScene;
import scenes.edf.characters.friendlies.EDFEarth;
import scenes.edf.characters.friendlies.EDFShip;
import scenes.edf.weapons.EDFWeaponBase;
import common.CommonMethod.BackGroundColor;

public class EDFCharacterController extends ShootingScene {
	EDFScene scene;
	EDFEarth earth;
	EDFShip ship;

	public EDFCharacterController(EDFScene scene) {
		BackGroundColor.BLACK.set();
		this.scene = scene;
		this.earth = add(new EDFEarth(scene));
		this.ship = add(new EDFShip(scene));
	}

	@Override
	public boolean updateProcess() {
		return super.updateProcess();
	}

	public EDFWeaponBase getRightWeapon() {
		return ship.getRightWeapon();
	}

	public EDFWeaponBase getLeftWeapon() {
		return ship.getLeftWeapon();
	}

	public boolean earthArrive() {
		return !earth.zeroHP();
	}
}
