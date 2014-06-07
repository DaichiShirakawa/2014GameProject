package classes.character.shooting;

import classes.scene.ShootingScene;

public abstract class ShootingCharacter extends ShootingObjectImpl {

	public ShootingCharacter(ShootingScene scene, float power) {
		super(scene, power);
	}

	public ShootingCharacter(ShootingScene scene, float power, float hp) {
		super(scene, power, hp);
	}

	@Override
	public float damage(float damage) {
		super.damage(damage);
		if (zeroHP()) {
			destroy();
		}
		return getHP();
	}

}