package scenes.tokishooting.weapons.bomb;

import static common.Commons.*;
import scenes.tokishooting.TSScene;
import scenes.tokishooting.weapons.TSBulletBase;
import scenes.tokishooting.weapons.TSWeaponBase;
import texture.Texture;
import texture.TextureLoader;
import classes.character.shooting.ShootingRotateCharacter;
import common.LR;

public class TSBombWeapon extends TSWeaponBase {
	static final float DISTANCE_FROM_OWNER = 12;
	static final int SHOOT_DELAY_FRAME = 120;
	public static final int MAX_CHARGE = 10;
	public static final Texture WEAPON_TEXTURE = TextureLoader.loadTexture(NAOKO_FOLDER_STRING
			+ "weapon-suika.png");
	private static final int RELOAD_COST = 10;

	public TSBombWeapon(TSScene scene, ShootingRotateCharacter owner,
			LR equipLR) {
		super(scene, owner, equipLR);
		setTexture(WEAPON_TEXTURE);
		setHeight(25);
		setWidth(8);
		setOffsetY(getOffsetY() + 5);
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
	protected TSBulletBase getBulletInstance() {
		return new BombBullet(getParentScene(), this);
	}

	@Override
	public int getReloadCost() {
		return RELOAD_COST;
	}
}
