package scenes.shootingtest;

import static java.lang.Math.*;
import classes.character.ShootingCharacter;
import classes.scene.ShootingScene;

public abstract class ShootingBulletCharacterImpl extends ShootingCharacter
		implements ShootingBulletCharacter {
	// パラメータ定数
	private ShootingCharacter shooter = null;
	private ShootingCharacter target = null;
	private float remainRange = 0;

	public ShootingBulletCharacterImpl(ShootingScene parentScene,
			ShootingCharacter shooter) {
		super(parentScene);
		this.shooter = shooter;
		setDivision(shooter.getDivision());
		setX(shooter.getX());
		setY(shooter.getY());
		setWidth(getBulletSize());
		setHeight(getBulletSize());
		setTexture(getBulletTexture());
		remainRange = getBulletRange();
	}

	public ShootingCharacter getParentCharacter() {
		return getShooter();
	}

	@Override
	public void update() {
		super.update();
		remainRange -= sqrt(pow(getVx(), 2) + pow(getVy(), 2));
		if (remainRange <= 0) {
			setDispose();
		}
	}

	@Override
	public float getBulletRange() {
		return 100_000;
	}

	@Override
	public void dispose() {
		return;
	}

	public ShootingCharacter getShooter() {
		return shooter;
	}

	public ShootingCharacter getTarget() {
		return target;
	}

	@Override
	public void setTarget(ShootingCharacter target) {
		this.target = target;
	}

	@Override
	public void hitEffect(ShootingCharacter target) {
		setDispose();
		target.takeDamage(getPower());
	}
}
