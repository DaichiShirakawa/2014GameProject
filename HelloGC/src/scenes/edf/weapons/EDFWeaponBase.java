package scenes.edf.weapons;

import java.lang.reflect.InvocationTargetException;

import classes.character.shooting.ShootingCharacter;
import classes.character.shooting.ShootingRotateCharacter;
import scenes.edf.EDFScene;
import common.LR;

public abstract class EDFWeaponBase extends ShootingRotateCharacter {
	private ShootingRotateCharacter owner;
	private int remainDelayFrame;
	private int remainBullet;
	private Class<? extends EDFBulletBase> bulletClass;

	public EDFWeaponBase(EDFScene scene, ShootingRotateCharacter owner,
			LR equipLR, Class<? extends EDFBulletBase> bulletClass) {
		super(scene, 0f);
		this.owner = owner;
		this.remainBullet = getMaxCharge();
		this.bulletClass = bulletClass;
		setTeam(owner.getTeam());
		setOffsetY(owner.getOffsetY());
		setOffsetX(getDistanceFromOwner() * equipLR.signum());
		setAngle(owner.getAngle());
	}

	@Override
	public EDFScene getParentScene() {
		return (EDFScene) super.getParentScene();
	}

	@Override
	public boolean updateProcess() {
		setAngle(owner.getAngle());
		remainDelayFrame--;
		return super.updateProcess();
	}

	public void shoot() {
		if (remainBullet <= 0 || 0 < remainDelayFrame) {
			return;
		}
		remainBullet--;
		remainDelayFrame = getShootDelayFrame();
		getParentScene().add(getBulletInstance());
	}

	private EDFBulletBase getBulletInstance() {
		try {
			return bulletClass.getConstructor(EDFScene.class,
					ShootingCharacter.class)
					.newInstance(getParentScene(), this);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			System.err.println("コンストラクタの呼び出しに失敗しました");
			e.printStackTrace();
			return null;
		}
	}

	abstract protected float getDistanceFromOwner();

	abstract protected int getShootDelayFrame();

	abstract public int getMaxCharge();

	public int getRemainBullet() {
		return remainBullet;
	}
	
	public int getRemainDelayFrame() {
		return remainDelayFrame;
	}

}