package scenes.edf.weapons.basic;

import static common.CommonMethod.*;

import java.awt.Color;

import scenes.edf.EDFScene;
import scenes.edf.weapons.EDFBulletBase;
import texture.Texture;
import texture.text.TextTextureMaker;
import classes.character.shooting.BasicEffect;
import classes.character.shooting.ShootingCharacter;
import classes.character.shooting.ShootingCharacterImpl;
import classes.scene.ShootingScene;

public class EDFBasicBullet extends EDFBulletBase {
	private static final int BULLET_POWER = 1;
	private static final int BULLET_SIZE = 8;
	private static final int BULLET_RANGE = 200;
	private static final Texture TEXTURE = TextTextureMaker.createText("弾");
	private static final float SPEED = 3;

	public EDFBasicBullet(EDFScene parentScene, ShootingCharacter shooter) {
		super(parentScene, shooter, BULLET_POWER);

		setColor(Color.white);

		double theta = Math.toRadians(-getShooter().getAngle());
		setVX(SPEED * (float) Math.sin(theta));
		setVY(SPEED * (float) Math.cos(theta));
		setVAngle(12);
	}

	@Override
	public float getBulletRange() {
		return BULLET_RANGE;
	}

	@Override
	public int getBulletSize() {
		return BULLET_SIZE;
	}

	@Override
	public Texture getBulletTexture() {
		return TEXTURE;
	}

	@Override
	public float getPower() {
		return BULLET_POWER;
	}

	@Override
	public void hitEffectTo(ShootingCharacter target) {
		super.hitEffectTo(target);
		for (int i = 0; i < 2; i++) {
			shoot(new Effect(getParentScene(), this));
		}
	}

	private class Effect extends BasicEffect {
		public Effect(ShootingScene parentScene, ShootingCharacterImpl shooter) {
			super(parentScene, shooter);
		}

		@Override
		public int getBulletSize() {
			return (int) (getShooter().getWidth() * random(0.5f, 0.8f));
		}
	}
}
