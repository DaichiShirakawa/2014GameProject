package scenes.edf.characters.friendlies.earth;

import io.Key;

import java.lang.reflect.InvocationTargetException;

import scenes.edf.EDFScene;
import scenes.edf.characters.friendlies.ship.EDFShip;
import scenes.edf.weapons.EDFWeaponBase;
import classes.character.shooting.ShootingRotateCharacter;
import classes.scene.ShootingScene;

import common.LR;

/**
 * 砦の基底クラス
 * 
 * @author shirakawa
 * 
 */
public abstract class EDFBasionBase extends ShootingRotateCharacter {

	private EDFEarth earth;
	private EDFShip ship;
	private Class<? extends EDFWeaponBase> weaponClass;

	public EDFBasionBase(ShootingScene scene, EDFEarth earth,
			Class<? extends EDFWeaponBase> weaponClass) {
		super(scene, 0);
		this.earth = earth;
		this.weaponClass = weaponClass;
	}

	@Override
	protected boolean updateProcess() {
		setAngle(earth.getAngle() + getPlaceAngle());
		setVAngle(earth.getVAngle());
		return super.updateProcess();
	}

	@Override
	protected boolean inputProcess() {
		if (ship != null) {
			if (Key.F.isPressing()) {
				changeWeapon(ship, LR.LEFT);
				ship.getWeapon(LR.LEFT)
						.reloadUpdate();
			}
			if (Key.J.isPressing()) {
				changeWeapon(ship, LR.RIGHT);
				ship.getWeapon(LR.RIGHT)
						.reloadUpdate();
			}
		}
		return super.inputProcess();
	}

	@Override
	public EDFScene getParentScene() {
		return (EDFScene) (super.getParentScene());
	}

	protected final void changeWeapon(EDFShip ship, LR lr) {
		if (ship.getWeapon(lr)
				.getClass() == getWeaponClass()) {
			return;
		}
		try {
			EDFWeaponBase weapon = getWeaponClass().getConstructor(
					EDFScene.class, ShootingRotateCharacter.class, LR.class)
					.newInstance(getParentScene(), ship, lr);
			getParentScene().addMoney(ship.getWeapon(lr).getSellValue());
			ship.equip(weapon, lr);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return;
		}
	}

	protected abstract float getPlaceAngle();

	public void landingShip(EDFShip ship) {
		this.ship = ship;
	}

	public void takeOffShip() {
		ship = null;
	}

	protected EDFShip getShip() {
		return ship;
	}

	public final Class<? extends EDFWeaponBase> getWeaponClass() {
		return weaponClass;
	}
}
