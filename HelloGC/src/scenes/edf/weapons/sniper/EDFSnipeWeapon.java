package scenes.edf.weapons.sniper;

import static common.Commons.*;

import java.awt.Color;

import scenes.edf.EDFScene;
import scenes.edf.weapons.EDFBulletBase;
import scenes.edf.weapons.EDFWeaponBase;
import texture.Texture;
import texture.TextureLoader;
import texture.text.TextTextureMaker;
import classes.character.shooting.ShootingRotateCharacter;
import classes.scene.ShootingScene;
import common.LR;

public class EDFSnipeWeapon extends EDFWeaponBase {
	static final float DISTANCE_FROM_OWNER = 8;
	static final int SHOOT_DELAY_FRAME = 60;
	public static final int MAX_CHARGE = 20;
	private static final Texture WEAPON_TEXTURE = TextureLoader.loadTexture(NAOKO_FOLDER_STRING
			+ "weapon-tikuwa.png");
	public static final Texture SNIPE_SITE_TEXTURE = TextTextureMaker.createText("|");
	private static final int RELOAD_COST = 5;

	public EDFSnipeWeapon(EDFScene scene, ShootingRotateCharacter owner,
			LR equipLR) {
		super(scene, owner, equipLR);
		setTexture(WEAPON_TEXTURE);
		setHeight(20);
		setWidth(6);
		setOffsetY(getOffsetY() + 5);
		add(new SnipeSite(scene, this));
	}

	@Override
	protected float getDistanceFromOwner() {
		return DISTANCE_FROM_OWNER;
	}

	@Override
	protected int getShootDelayFrame() {
		return SHOOT_DELAY_FRAME;
	}

	@Override
	public int getMaxBullet() {
		return MAX_CHARGE;
	}

	@Override
	protected EDFBulletBase getBulletInstance() {
		return new SnipeBullet(getParentScene(), this);
	}

	private class SnipeSite extends ShootingRotateCharacter {
		private EDFSnipeWeapon weapon;

		public SnipeSite(ShootingScene scene, EDFSnipeWeapon weapon) {
			super(scene, 0);
			this.weapon = weapon;
			setTexture(SNIPE_SITE_TEXTURE);
			setWidth(5);
			setHeight((int) (WIDTH / 2 * 1.5f));
			setColor(Color.red);
			setAlpha(0.5f);
		}

		@Override
		protected boolean updateProcess() {
			setOffsetX(weapon.getOffsetX());
			setOffsetY(weapon.getOffsetY() + getHeight() / 2 + 3);
			setAngle(weapon.getAngle());
			return super.updateProcess();
		}
	}

	@Override
	public int getReloadCost() {
		return RELOAD_COST;
	}
}
