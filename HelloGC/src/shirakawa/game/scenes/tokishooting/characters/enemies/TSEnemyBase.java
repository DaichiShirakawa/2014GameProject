package shirakawa.game.scenes.tokishooting.characters.enemies;

import static shirakawa.game.common.Commons.*;

import java.awt.Color;

import shirakawa.game.classes.character.DamagePopupCharacter;
import shirakawa.game.classes.character.shooting.ShootingBasicEffect;
import shirakawa.game.classes.character.shooting.ShootingCharacter;
import shirakawa.game.classes.character.shooting.ShootingRotateCharacter;
import shirakawa.game.classes.scene.ShootingScene;
import shirakawa.game.common.LR;
import shirakawa.game.scenes.tokishooting.TokiShootingScene;
import shirakawa.game.scenes.tokishooting.characters.friendlies.earth.TSEarth;
import shirakawa.game.scenes.tokishooting.characters.friendlies.ship.TSShip;

/**
 * 敵キャラの基底クラス
 * 
 * @author shirakawa
 *
 */
public abstract class TSEnemyBase extends ShootingRotateCharacter {

	TSEnemyProperty property;

	public TSEnemyBase(ShootingScene parentScene, float bornAngle, LR lr,
			TSEnemyProperty property) {
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
	public TokiShootingScene getParentScene() {
		return (TokiShootingScene) super.getParentScene();
	}

	@Override
	protected void destroyProcess() {
		super.destroyProcess();
		for (int i = 0; i < 5; i++) {
			getParentScene().add(new ShootingBasicEffect(getParentScene(), this));
		}
	}

	public int getMoney() {
		return property.money;
	}

	@Override
	public void hitEffectTo(ShootingCharacter target) {
		if(target instanceof TSShip) {
			return;
		}
		if ((0 < getPower()) && (target instanceof TSEarth)) {
			getParentScene().add(
					new DamagePopupCharacter(target, (int) getPower()));
		}
		super.hitEffectTo(target);
	}
}