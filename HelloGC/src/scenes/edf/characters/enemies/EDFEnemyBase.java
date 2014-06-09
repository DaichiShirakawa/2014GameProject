package scenes.edf.characters.enemies;

import static common.Commons.*;

import java.awt.Color;

import scenes.edf.EDFScene;
import classes.character.DamagePopupCharacter;
import classes.character.shooting.BasicEffect;
import classes.character.shooting.ShootingBulletCharacter;
import classes.character.shooting.ShootingCharacter;
import classes.character.shooting.ShootingRotateCharacter;
import classes.scene.ShootingScene;
import common.LR;

public abstract class EDFEnemyBase extends ShootingRotateCharacter {

	EDFEnemyProperty property;

	public EDFEnemyBase(ShootingScene parentScene, float bornAngle, LR lr,
			EDFEnemyProperty property) {
		super(parentScene, property.power, property.hp);
		this.property = property;
		setWidth(property.size);
		setHeight(property.size);
		// どの角度だろうと外周ピッタリの位置で生成する計算式
		float distanceToOuther = (float) (1 / Math.max(
				Math.abs(Math.sin(Math.toRadians(bornAngle))),
				Math.abs(Math.cos(Math.toRadians(bornAngle)))));
		setOffsetY((WIDTH / 2 * distanceToOuther) + property.size);
		setVOffsetY(-property.fallSpeed);
		setAngle(bornAngle);
		setVAngle(property.rotateSpeed * lr.signum());
		setTexture(property.texture);
		setTeam(ShootingTeam.ENEMY_TEAM);
		setColor(createColor());
	}

	protected abstract Color createColor();

	@Override
	public EDFScene getParentScene() {
		return (EDFScene) super.getParentScene();
	}

	@Override
	protected void destroyProcess() {
		super.destroyProcess();
		for (int i = 0; i < 5; i++) {
			getParentScene().add(new BasicEffect(getParentScene(), this));
		}
	}

	public int getMoney() {
		return property.money;
	}

	@Override
	public void hitEffectTo(ShootingCharacter target) {
		if (!(target instanceof ShootingBulletCharacter)) {
			getParentScene().add(
					new DamagePopupCharacter(target, (int) getPower()));
		}
		super.hitEffectTo(target);
	}
}
