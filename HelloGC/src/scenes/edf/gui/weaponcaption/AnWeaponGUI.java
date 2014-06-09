package scenes.edf.gui.weaponcaption;

import java.awt.Color;

import scenes.edf.weapons.EDFWeaponBase;
import classes.character.GameCharacterImpl;
import classes.character.SimpleCharacter;
import classes.character.TextCharacter;

import common.Commons;
import common.LR;

/**
 * 武器単体GUI
 * 
 * @author shirakawa
 * 
 */
class AnWeaponGUI extends GameCharacterImpl {
	private EDFWeaponBase weapon;
	private int currentRemainBullet;

	private SimpleCharacter weaponView;
	private TextCharacter bulletCountText;

	public AnWeaponGUI(LR lr, EDFWeaponBase weapon) {
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
		add(new DelayBar(this));

		setWeapon(weapon);
	}

	public EDFWeaponBase getWeapon() {
		return weapon;
	}

	public void setWeapon(EDFWeaponBase weapon) {
		this.weapon = weapon;
		weaponView.setTexture(weapon.getTexture());
		currentRemainBullet = weapon.getRemainBullet();
		bulletCountText.updateText(getBulletCountText(weapon));
	}

	private String getBulletCountText(EDFWeaponBase weapon) {
		return weapon.getRemainBullet() + " / " + weapon.getMaxBullet();
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