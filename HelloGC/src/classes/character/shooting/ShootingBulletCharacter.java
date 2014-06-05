package classes.character.shooting;

import static java.lang.Math.*;
import texture.Texture;
import classes.scene.ShootingScene;

public abstract class ShootingBulletCharacter extends ShootingObjectImpl {
	private ShootingObject shooter = null;
	private ShootingObject target = null;
	private float remainRange = 0;

	public ShootingBulletCharacter(ShootingScene parentScene,
			ShootingObject shooter, float power, float hp) {
		super(parentScene, power, hp);
		this.shooter = shooter;
		this.remainRange = getBulletRange();
		setTeam(shooter.getTeam());
		setX(shooter.getX());
		setY(shooter.getY());
		setWidth(getBulletSize());
		setHeight(getBulletSize());
		setTexture(getBulletTexture());
	}

	public ShootingBulletCharacter(ShootingScene parentScene,
			ShootingObject shooter, float power) {
		this(parentScene, shooter, power, 1);
	}

	abstract protected Texture getBulletTexture();

	abstract protected int getBulletSize();

	public ShootingObject getParentCharacter() {
		return getShooter();
	}

	@Override
	public void update() {
		super.update();
		remainRange -= sqrt(pow(getVX(), 2) + pow(getVY(), 2));
		if (remainRange <= 0) {
			setDestroy();
		}
	}

	public float getBulletRange() {
		return 200;
	}

	@Override
	public void dispose() {
		return;
	}

	protected ShootingObject getShooter() {
		return shooter;
	}

	public ShootingObject getTarget() {
		return target;
	}

	protected void setTarget(ShootingObject target) {
		this.target = target;
	}

	@Override
	public float damage(float damage) {
		super.damage(damage);
		if (zeroHP()) {
			dead();
		}
		return getHP();

	}

	protected void dead() {
		setDestroy();
	}
}
