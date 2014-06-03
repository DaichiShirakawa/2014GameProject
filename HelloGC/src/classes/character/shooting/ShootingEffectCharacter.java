package classes.character.shooting;

import classes.scene.ShootingScene;

public abstract class ShootingEffectCharacter extends ShootingBulletCharacter {

	public ShootingEffectCharacter(ShootingScene parentScene,
			ShootingObject shooter) {
		super(parentScene, shooter, 0);
		disposeAfter(getLifeTime());
		setTeam(TEAM.NO_TEAM);
	}

	abstract protected float getLifeTime();
	
	@Override
	public float damage(float damage) {
		return 0;
	}

	@Override
	public void hitEffectTo(ShootingObject target) {
		return;
	}
}