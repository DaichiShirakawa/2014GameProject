package scenes.edf.weapons;

import classes.character.shooting.ShootingBulletCharacter;
import classes.character.shooting.ShootingCharacter;
import scenes.edf.EDFScene;
import scenes.edf.characters.enemies.EDFEnemyBase;

public abstract class EDFBulletBase extends ShootingBulletCharacter {

	public EDFBulletBase(EDFScene parentScene, ShootingCharacter shooter,
			float power) {
		this(parentScene, shooter, power, 1);
	}

	public EDFBulletBase(EDFScene parentScene, ShootingCharacter shooter,
			float power, float hp) {
		super(parentScene, shooter, power, hp);
	}

	@Override
	public EDFScene getParentScene() {
		return (EDFScene) super.getParentScene();
	}

	@Override
	public void hitEffectTo(ShootingCharacter target) {
		super.hitEffectTo(target);
		if (target.isDestroyed() && target instanceof EDFEnemyBase) {
			((EDFScene) getParentScene()).addMoney(((EDFEnemyBase) target).getMoney());
		}
	}
}
