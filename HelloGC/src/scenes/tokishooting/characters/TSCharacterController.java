package scenes.tokishooting.characters;

import static common.CommonMethod.*;

import java.util.Iterator;

import scenes.tokishooting.TokiShootingScene;
import scenes.tokishooting.characters.friendlies.earth.TSBasionBase;
import scenes.tokishooting.characters.friendlies.earth.TSEarth;
import scenes.tokishooting.characters.friendlies.ship.TSShip;
import scenes.tokishooting.weapons.TSWeaponBase;
import classes.GameObject;
import classes.scene.ShootingScene;
import common.LR;

/**
 * 自軍、敵軍、弾丸はこのクラスで管理される。
 * 
 * @author shirakawa
 *
 */
public class TSCharacterController extends ShootingScene {
	TokiShootingScene scene;
	TSEarth earth;
	TSShip ship;

	public TSCharacterController(TokiShootingScene scene) {
		this.scene = scene;
		this.earth = add(new TSEarth(scene));
		this.ship = add(new TSShip(scene, this));
	}

	@Override
	public boolean updateProcess() {
		return super.updateProcess();
	}
	
	public TSWeaponBase getWeapon(LR lr) {
		return ship.getWeapon(lr);
	}

	public boolean earthArrive() {
		return !earth.zeroHP();
	}

	public TSBasionBase getBackBase() {
		for (Iterator<GameObject> ite = earth.getIterator(); ite.hasNext();) {
			GameObject go = ite.next();
			if (!(go instanceof TSBasionBase)) {
				continue;
			}

			//船と基地の角度の差が45°以内なら帰還できる
			float basionAngle = toAbsoluteAngle(((TSBasionBase) go).getAngle());
			float shipAngle = toAbsoluteAngle(ship.getAngle());

			if (Math.abs(shipAngle - basionAngle) <= 45) {
				return (TSBasionBase) go;
			}
		}
		return null;
	}

	public TSShip getShip() {
		return ship;
	}
}
