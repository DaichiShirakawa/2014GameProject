package classes.character.shooting;

import classes.scene.ShootingScene;

public abstract class ShootingEffectCharacter extends ShootingBulletCharacter {

	public ShootingEffectCharacter(ShootingScene parentScene,
			ShootingCharacter shooter) {
		super(parentScene, shooter, 0);
		destroyAfter(getLifeTime());
		setTeam(ShootingTeam.NO_TEAM);
	}

	abstract protected float getLifeTime();
	
	@Override
	public float damage(float damage) {
		return 0;
	}

	@Override
	public void hitEffectTo(ShootingCharacter target) {
		return;
	}
}