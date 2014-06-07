package classes.character.shooting;

import scenes.edf.weapons.BasicBullet;
import classes.scene.ShootingScene;

import common.LR;

public abstract class ShootingWeaponCharacter extends ShootingObjectImpl {
	private ShootingObjectImpl owner;
	private LR equipLR;
	private int remainShootDelayFrame;
	private int remainBullet;

	public ShootingWeaponCharacter(ShootingScene scene,
			ShootingCharacter owner, LR equipLR) {
		super(scene, 0f);
		this.owner = owner;
		this.equipLR = equipLR;
		this.remainBullet = getMaxCharge();
	}

	@Override
	public boolean updateProcess() {
		setAngle(owner.getAngle());

		double theta = Math.toRadians(getAngle());

		setX(owner.getX() + (5 * (float) Math.sin(-theta)));
		setY(owner.getY() + (5 * (float) Math.cos(-theta)));

		setX(getX()
				+ (getDistanceFromOwner() * equipLR.signum() * (float) Math.cos(theta)));
		setY(getY()
				+ (getDistanceFromOwner() * equipLR.signum() * (float) Math.sin(theta)));

		remainShootDelayFrame--;
		return super.updateProcess();
	}

	public void shoot() {
		if (remainBullet <= 0 || 0 < remainShootDelayFrame) {
			return;
		}
		remainBullet--;
		remainShootDelayFrame = getShootDelayFrame();
		getParentScene().add(new BasicBullet(getParentScene(), this));
	}

	abstract protected float getDistanceFromOwner();

	abstract protected int getShootDelayFrame();

	abstract public int getMaxCharge();

	public int getRemainBullet() {
		return remainBullet;
	}

}