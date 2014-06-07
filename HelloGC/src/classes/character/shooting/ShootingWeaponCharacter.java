package classes.character.shooting;

import scenes.edf.weapons.BasicBullet;
import classes.scene.ShootingScene;

import common.LR;

public abstract class ShootingWeaponCharacter extends ShootingRotateCharacter {
	private ShootingRotateCharacter owner;
	private int remainShootDelayFrame;
	private int remainBullet;

	public ShootingWeaponCharacter(ShootingScene scene,
			ShootingRotateCharacter owner, LR equipLR) {
		super(scene, 0f);
		this.owner = owner;
		this.remainBullet = getMaxCharge();
		setTeam(owner.getTeam());
		setOffsetY(owner.getOffsetY());
		setOffsetX(getDistanceFromOwner() * equipLR.signum());
		setAngle(owner.getAngle());
	}

	@Override
	public boolean updateProcess() {
		setAngle(owner.getAngle());

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