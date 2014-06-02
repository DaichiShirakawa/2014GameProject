package scenes.edf.enemies;

import static common.Commons.*;

import java.awt.Color;

import scenes.edf.EDFScene;
import scenes.edf.weapons.BasicEffect;
import texture.Texture;
import texture.text.TextTextureMaker;
import classes.character.GameCharacter;
import classes.character.ShootingCharacter;
import classes.scene.ShootingScene;

import common.LR;

public class EnemyCharacter extends ShootingCharacter {
	private static final int SIZE = 20;
	private static final Texture TEXTURE = TextTextureMaker.createText("▽");
	private static final float ROTATE_SPEED = 0.2f;
	private static final float FALL_SPEED = 0.2f;

	private float height;
	private int hp = 3;

	public EnemyCharacter(ShootingScene parentScene, float bornAngle, LR lr) {
		super(parentScene);
		setWidth(SIZE);
		setHeight(SIZE);
		height = WIDTH / 2;
		setAngle(bornAngle);
		setVAngle(ROTATE_SPEED * lr.signum());
		setTexture(TEXTURE);
		setDivision(DIVISION.ENEMY);
		setColor(Color.orange);
	}

	@Override
	public void update() {
		super.update();
		height -= FALL_SPEED;
	}

	@Override
	public void dispose() {
		//
	}

	@Override
	public GameCharacter setAngle(float angle) {
		super.setAngle(angle);
		double theta = Math.toRadians(-angle);
		setX(CENTER_X + height * (float) Math.sin(theta));
		setY(CENTER_Y + height * (float) Math.cos(theta));
		return this;
	}

	@Override
	public void takeDamage(float damage) {
		hp -= damage;
		if (hp <= 0) {
			dead();
		}
	}

	private void dead() {
		for (int i = 0; i < 5; i++) {
			getParentScene().shoot(new BasicEffect(getParentScene(), this));
		}
		setDispose();
		((EDFScene) getParentScene()).addMoney(100);
	}
}
