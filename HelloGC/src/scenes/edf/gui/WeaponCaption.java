package scenes.edf.gui;

import java.awt.Color;

import scenes.edf.EDFScene;
import scenes.edf.weapons.BasicWeapon;
import classes.character.GameCharacterImpl;
import classes.character.SimpleCharacter;
import classes.character.TextCharacter;

import common.Commons;
import common.LR;

public class WeaponCaption extends GameCharacterImpl {
	EDFScene scene;

	private Hoge left;
	private Hoge right;

	public WeaponCaption(EDFScene scene) {
		this.scene = scene;
		left = new Hoge(LR.LEFT, scene.getLeftWeapon());
		right = new Hoge(LR.RIGHT, scene.getRightWeapon());
	}

	@Override
	public void update() {
		if (scene.getLeftWeapon() != left.weapon) {
			left.setWeapon(scene.getLeftWeapon());
		}
		if (scene.getRightWeapon() != right.weapon) {
			right.setWeapon(scene.getRightWeapon());
		}
		left.update();
		right.update();
	}

	@Override
	public void render() {
		left.render();
		right.render();
	}

	@Override
	public void dispose() {
		left.dispose();
		right.dispose();
	}

	@Override
	public boolean canDispose() {
		return false;
	}

	private class Hoge extends GameCharacterImpl {
		private BasicWeapon weapon;
		private int currentRemainBullet;

		private SimpleCharacter weaponView = new SimpleCharacter();
		private TextCharacter remainBulletView = new TextCharacter();

		public Hoge(LR lr, BasicWeapon weapon) {
			setWeapon(weapon);

			setX(Commons.CENTER_X + (165 * lr.signum()));
			setY(40);

			weaponView.setX(getX())
					.setY(getY() + 5)
					.setWidth(weapon.getWidth())
					.setHeight(weapon.getHeight())
					.setScale(2)
					.setColor(weapon.getColor());
			remainBulletView.setX(getX())
					.setY(getY() - 25)
					.setScale(0.3f)
					.setColor(Color.white);
		}

		private void setWeapon(BasicWeapon weapon) {
			this.weapon = weapon;
			currentRemainBullet = weapon.getRemainBullet();
			weaponView.setTexture(weapon.getTexture());
			remainBulletView.updateText(getRemainBullet(weapon));
		}

		private String getRemainBullet(BasicWeapon weapon) {
			return weapon.getRemainBullet() + " / " + weapon.getMaxCharge();
		}

		@Override
		public void update() {
			if (currentRemainBullet != weapon.getRemainBullet()) {
				remainBulletView.updateText(getRemainBullet(weapon));
			}
		}

		@Override
		public void render() {
			weaponView.render();
			remainBulletView.render();
		}

		@Override
		public void dispose() {
			super.dispose();
			weaponView.dispose();
			remainBulletView.dispose();
		}
	}
}
