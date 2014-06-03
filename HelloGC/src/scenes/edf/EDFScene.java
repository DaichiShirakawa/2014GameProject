package scenes.edf;

import main.FPSManager;
import scenes.edf.enemies.EDFEnemy;
import scenes.edf.gui.MoneyCaption;
import scenes.edf.gui.WeaponCaption;
import scenes.edf.weapons.BasicWeapon;
import classes.character.shooting.ShootingWeaponCharacter;
import classes.scene.ShootingScene;
import common.LR;
import common.CommonMethod.BackGroundColor;

public class EDFScene extends ShootingScene {
	EDFShip edfShip;
	private static final int ENEMY_SPAWN_INTERVAL = 30;

	private int money = 0;

	public EDFScene() {
		BackGroundColor.BLACK.set();

		add(new EDFEarth(this));

		edfShip = add(new EDFShip(this));
		edfShip.equipLeft(new BasicWeapon(this, edfShip, LR.LEFT));
		edfShip.equipRight(new BasicWeapon(this, edfShip, LR.RIGHT));

		add(new MoneyCaption(this));
		add(new WeaponCaption(this));
	}

	public int getMoney() {
		return money;
	}

	public int addMoney(int value) {
		money += value;
		return money;
	}

	public ShootingWeaponCharacter getRightWeapon() {
		return edfShip.getRightWeapon();
	}

	public ShootingWeaponCharacter getLeftWeapon() {
		return edfShip.getLeftWeapon();
	}

	@Override
	public void update() {
		super.update();
		if (FPSManager.totalFrame() % ENEMY_SPAWN_INTERVAL == 0) {
			add(new EDFEnemy(this, FPSManager.totalFrame(), LR.RIGHT));
		}
	}
}
