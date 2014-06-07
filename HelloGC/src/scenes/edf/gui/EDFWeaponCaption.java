package scenes.edf.gui;

import java.awt.Color;

import scenes.edf.EDFMainCharacterController;
import classes.character.GameCharacterImpl;
import classes.character.SimpleCharacter;
import classes.character.TextCharacter;
import classes.character.shooting.EDFWeaponCharacter;

import common.Commons;
import common.LR;

public class EDFWeaponCaption extends GameCharacterImpl {
	EDFMainCharacterController scene;

	private AnWeaponGUI leftGUI;
	private AnWeaponGUI rightGUI;

	public EDFWeaponCaption(EDFMainCharacterController edfCharacterController) {
		this.scene = edfCharacterController;
		leftGUI = add(new AnWeaponGUI(LR.LEFT,
				edfCharacterController.getLeftWeapon()));
		rightGUI = add(new AnWeaponGUI(LR.RIGHT,
				edfCharacterController.getRightWeapon()));
	}

	@Override
	public boolean updateProcess() {
		if (scene.getLeftWeapon() != leftGUI.weapon) {
			leftGUI.setWeapon(scene.getLeftWeapon());
		}
		if (scene.getRightWeapon() != rightGUI.weapon) {
			rightGUI.setWeapon(scene.getRightWeapon());
		}
		return true;
	}

	@Override
	protected boolean canDisposeTexture() {
		return false;
	}

	/**
	 * 片側の武器GUI
	 * 
	 * @author shirakawa
	 * 
	 */
	private class AnWeaponGUI extends GameCharacterImpl {
		private EDFWeaponCharacter weapon;
		private int currentRemainBullet;

		private SimpleCharacter weaponView;
		private TextCharacter bulletCountText;

		public AnWeaponGUI(LR lr, EDFWeaponCharacter weapon) {
			setX(Commons.CENTER_X + (165 * lr.signum()));
			setY(40);

			weaponView = add(new SimpleCharacter());
			weaponView.setX(getX())
					.setY(getY() + 5)
					.setWidth(weapon.getWidth())
					.setHeight(weapon.getHeight())
					.setScale(2)
					.setColor(weapon.getColor());
			bulletCountText = add(new TextCharacter());
			bulletCountText.setX(getX())
					.setY(getY() - 25)
					.setScale(0.3f)
					.setColor(Color.white);

			setWeapon(weapon);
		}

		private void setWeapon(EDFWeaponCharacter weapon) {
			this.weapon = weapon;
			weaponView.setTexture(weapon.getTexture());
			currentRemainBullet = weapon.getRemainBullet();
			bulletCountText.updateText(getBulletCountText(weapon));
		}

		private String getBulletCountText(EDFWeaponCharacter weapon) {
			return weapon.getRemainBullet() + " / " + weapon.getMaxCharge();
		}

		@Override
		public boolean updateProcess() {
			if (currentRemainBullet == weapon.getRemainBullet()) {
				return true;
			}
			bulletCountText.updateText(getBulletCountText(weapon));
			currentRemainBullet = weapon.getRemainBullet();
			return true;
		}

		@Override
		protected boolean canDisposeTexture() {
			return false;
		}
	}
}
