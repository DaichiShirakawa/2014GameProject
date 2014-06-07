package scenes.edf.enemies;

import static common.Commons.*;

import java.awt.Color;

import scenes.edf.EDFScene;
import scenes.edf.weapons.BasicEffect;
import texture.Texture;
import texture.text.TextTextureMaker;
import classes.character.shooting.ShootingRotateCharacter;
import classes.scene.ShootingScene;

import common.LR;

public class EDFEnemy extends ShootingRotateCharacter {
	private static final int POWER = 1;
	private static final int HP = 3;
	private static final int SIZE = 20;
	private static final Texture TEXTURE = TextTextureMaker.createText("▽");
	private static final float ROTATE_SPEED = 0.2f;
	private static final float FALL_SPEED = 0.2f;

	public EDFEnemy(ShootingScene parentScene, float bornAngle, LR lr) {
		super(parentScene, POWER, HP);
		setWidth(SIZE);
		setHeight(SIZE);
		setOffsetY(WIDTH / 2);
		setVOffsetY(-FALL_SPEED);
		setAngle(bornAngle);
		setVAngle(ROTATE_SPEED * lr.signum());
		setTexture(TEXTURE);
		setTeam(SHOOTING_TEAM.ENEMY_TEAM);
		setColor(Color.orange);
	}

	@Override
	protected void destroyProcess() {
		super.destroyProcess();
		for (int i = 0; i < 5; i++) {
			getParentScene().add(new BasicEffect(getParentScene(), this));
		}
		((EDFScene) getParentScene()).addMoney(10);

	}
}
