package scenes.edf.weapons;

import static common.CommonMethod.*;

import java.awt.Color;

import scenes.shootingtest.ShootingBulletCharacterImpl;
import texture.Texture;
import texture.text.TextTextureMaker;
import classes.character.ShootingCharacter;
import classes.scene.ShootingScene;

public class BasicBullet extends ShootingBulletCharacterImpl {
	private static final Texture TEXTURE = TextTextureMaker.createText("弾");
	private static final float SPEED = 3;

	public BasicBullet(ShootingScene parentScene, ShootingCharacter shooter) {
		super(parentScene, shooter);

		setColor(Color.white);

		double theta = Math.toRadians(-getShooter().getAngle());
		setVx(SPEED * (float) Math.sin(theta));
		setVy(SPEED * (float) Math.cos(theta));
		setVAngle(12);
	}

	@Override
	public float getBulletRange() {
		return 300;
	}

	@Override
	public int getBulletSize() {
		return 8;
	}

	@Override
	public Texture getBulletTexture() {
		return TEXTURE;
	}

	@Override
	public float getPower() {
		return 1;
	}

	@Override
	public void hitEffect(ShootingCharacter target) {
		super.hitEffect(target);
		for (int i = 0; i < 2; i++) {
			shoot(new Effect(getParentScene(), this));
		}
	}

	private class Effect extends BasicEffect {
		public Effect(ShootingScene parentScene, ShootingCharacter shooter) {
			super(parentScene, shooter);
		}

		@Override
		public int getBulletSize() {
			return (int) (getShooter().getWidth() * random(0.5f, 0.8f));
		}
	}
}
