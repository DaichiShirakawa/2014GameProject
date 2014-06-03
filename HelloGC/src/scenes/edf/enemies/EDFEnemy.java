package scenes.edf.enemies;

import static common.Commons.*;

import java.awt.Color;

import scenes.edf.EDFScene;
import scenes.edf.weapons.BasicEffect;
import texture.Texture;
import texture.text.TextTextureMaker;
import classes.character.shooting.RotateShootingCharacter;
import classes.scene.ShootingScene;

import common.LR;

public class EDFEnemy extends RotateShootingCharacter {
	private static final int HP = 3;
	private static final int SIZE = 20;
	private static final Texture TEXTURE = TextTextureMaker.createText("▽");
	private static final float ROTATE_SPEED = 0.2f;
	private static final float FALL_SPEED = 0.2f;

	public EDFEnemy(ShootingScene parentScene, float bornAngle, LR lr) {
		super(parentScene, 1, HP);
		setWidth(SIZE);
		setHeight(SIZE);
		setElevation(WIDTH / 2);
		setVElevation(-FALL_SPEED);
		setAngle(bornAngle);
		setVAngle(ROTATE_SPEED * lr.signum());
		setTexture(TEXTURE);
		setTeam(TEAM.ENEMY_TEAM);
		setColor(Color.orange);
	}

	@Override
	protected void dead() {
		super.dead();
		for (int i = 0; i < 5; i++) {
			getParentScene().shoot(new BasicEffect(getParentScene(), this));
		}
		((EDFScene) getParentScene()).addMoney(100);
	}
}
