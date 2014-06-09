package scenes.edf.weapons.basic;

import java.awt.Color;

import scenes.edf.EDFScene;
import scenes.edf.weapons.EDFWeaponBase;
import texture.Texture;
import texture.text.TextTextureMaker;
import classes.character.shooting.ShootingRotateCharacter;
import common.LR;

public class EDFBasicWeapon extends EDFWeaponBase {
	static final float DISTANCE_FROM_OWNER = 8;
	static final int SHOOT_DELAY_FRAME = 18;
	public static final int MAX_CHARGE = 50;
	public static final Texture WEAPON_TEXTURE = TextTextureMaker.createText("↑");

	public EDFBasicWeapon(EDFScene scene, ShootingRotateCharacter owner, LR equipLR) {
		super(scene, owner, equipLR, EDFBasicBullet.class);
		setTexture(WEAPON_TEXTURE);
		setHeight(20);
		setWidth(10);
		setColor(Color.white);
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
}
