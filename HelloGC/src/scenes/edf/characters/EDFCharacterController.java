package scenes.edf.characters;

import static common.CommonMethod.*;

import java.util.Iterator;

import scenes.edf.EDFScene;
import scenes.edf.characters.friendlies.earth.EDFBasionBase;
import scenes.edf.characters.friendlies.earth.EDFEarth;
import scenes.edf.characters.friendlies.ship.EDFShip;
import scenes.edf.weapons.EDFWeaponBase;
import classes.GameObject;
import classes.scene.ShootingScene;

import common.LR;

public class EDFCharacterController extends ShootingScene {
	EDFScene scene;
	EDFEarth earth;
	EDFShip ship;

	public EDFCharacterController(EDFScene scene) {
		this.scene = scene;
		this.earth = add(new EDFEarth(scene));
		this.ship = add(new EDFShip(scene, this));
	}

	@Override
	public boolean updateProcess() {
		return super.updateProcess();
	}
	
	public EDFWeaponBase getWeapon(LR lr) {
		return ship.getWeapon(lr);
	}

	public boolean earthArrive() {
		return !earth.zeroHP();
	}

	public EDFBasionBase getBackBase() {
		for (Iterator<GameObject> ite = earth.getIterator(); ite.hasNext();) {
			GameObject go = ite.next();
			if (!(go instanceof EDFBasionBase)) {
				continue;
			}

			//船と基地の角度の差が45°以内なら帰還できる
			float basionAngle = toAbsoluteAngle(((EDFBasionBase) go).getAngle());
			float shipAngle = toAbsoluteAngle(ship.getAngle());

			if (Math.abs(shipAngle - basionAngle) <= 45) {
				return (EDFBasionBase) go;
			}
		}
		return null;
	}

	public EDFShip getShip() {
		return ship;
	}
}
