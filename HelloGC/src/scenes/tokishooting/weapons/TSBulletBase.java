package scenes.tokishooting.weapons;

import classes.character.shooting.ShootingBulletCharacter;
import classes.character.shooting.ShootingCharacter;
import scenes.tokishooting.TSScene;
import scenes.tokishooting.characters.enemies.TSEnemyBase;

public abstract class TSBulletBase extends ShootingBulletCharacter {

	public TSBulletBase(TSScene parentScene, ShootingCharacter shooter,
			float power) {
		this(parentScene, shooter, power, 1);
	}

	public TSBulletBase(TSScene parentScene, ShootingCharacter shooter,
			float power, float hp) {
		super(parentScene, shooter, power, hp);
	}

	@Override
	public TSScene getParentScene() {
		return (TSScene) super.getParentScene();
	}

	@Override
	public void hitEffectTo(ShootingCharacter target) {
		super.hitEffectTo(target);
		if (target.isDestroyed() && target instanceof TSEnemyBase) {
			((TSScene) getParentScene()).addMoney(((TSEnemyBase) target).getMoney());
		}
	}
}
