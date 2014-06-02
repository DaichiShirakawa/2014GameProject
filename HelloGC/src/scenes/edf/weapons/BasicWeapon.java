package scenes.edf.weapons;

import java.awt.Color;

import common.LR;
import classes.character.ShootingCharacter;
import classes.scene.ShootingScene;
import texture.Texture;
import texture.text.TextTextureMaker;

public class BasicWeapon extends ShootingCharacter {
	private static final float DISTANCE_FROM_OWNER = 8;
	private static final int SHOOT_DELAY_FRAME = 20;
	public static final Texture WEAPON_TEXTURE = TextTextureMaker.createText("↑");
	public static final int MAX_CHARGE = 30;

	private ShootingCharacter owner = null;
	private LR equip;
	private int remainShootDelay = 0;
	private int remainBullet = MAX_CHARGE;

	public BasicWeapon(ShootingScene scene, ShootingCharacter owner, LR equip) {
		super(scene);
		this.owner = owner;
		this.equip = equip;
		setTexture(WEAPON_TEXTURE);
		setHeight(20);
		setWidth(10);
		setColor(Color.white);
		setDivision(owner.getDivision());
	}

	@Override
	public void update() {
		super.update();
		setAngle(owner.getAngle());
		double theta = Math.toRadians(getAngle());

		setX(owner.getX() + (5 * (float) Math.sin(-theta)));
		setY(owner.getY() + (5 * (float) Math.cos(-theta)));

		setX(getX()
				+ (DISTANCE_FROM_OWNER * equip.signum() * (float) Math.cos(theta)));
		setY(getY()
				+ (DISTANCE_FROM_OWNER * equip.signum() * (float) Math.sin(theta)));

		remainShootDelay--;
	}

	public void shoot() {
		if (remainBullet <= 0 || 0 < remainShootDelay) {
			return;
		}
		remainBullet--;
		remainShootDelay = SHOOT_DELAY_FRAME;
		getParentScene().shoot(new BasicBullet(getParentScene(), this));
	}

	@Override
	public void dispose() {
		return;
	}

	public int getMaxCharge() {
		return MAX_CHARGE;
	}

	public int getRemainBullet() {
		return remainBullet;
	}
}
