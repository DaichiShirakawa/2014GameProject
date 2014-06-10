package scenes.tokishooting.weapons.bomb;

import static common.CommonMethod.*;
import static common.Commons.*;
import scenes.tokishooting.TSScene;
import scenes.tokishooting.weapons.TSBulletBase;
import texture.Texture;
import texture.TextureLoader;
import classes.character.shooting.BasicEffect;
import classes.character.shooting.ShootingCharacter;
import classes.character.shooting.ShootingCharacterImpl;
import classes.scene.ShootingScene;

class BombChildBullet extends TSBulletBase {
	private static final int BULLET_POWER = 1;
	private static final int BULLET_HP = 5;
	private static final int BULLET_SIZE = 8;
	private static final int BULLET_RANGE = 100;
	private static final Texture TEXTURE = TextureLoader.loadTexture(NAOKO_FOLDER_STRING
			+ "bullet-suikaChild.png");
	private static final float SPEED = 2.5f;
	private boolean smokeUsed = false;

	public BombChildBullet(TSScene parentScene, ShootingCharacter shooter) {
		super(parentScene, shooter, BULLET_POWER, BULLET_HP);

		double theta = Math.toRadians(random(0, 360));
		setVX(SPEED * (float) Math.sin(theta));
		setVY(SPEED * (float) Math.cos(theta));
		setScale(random(1, 3));
		setVScale(-0.07f);
		setVAngle(1);
	}

	@Override
	public boolean updateProcess() {
		if (!smokeUsed && getScale() <= 0.3f) {
			smokeUsed = true;
			getParentScene().add(new SmokeEffect(getParentScene(), this, false));
		}
		if (getScale() <= 0.1f) {
			destroy();
		}
		return super.updateProcess();
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
			setScale(random(1f, 1.5f));
		}

		@Override
		public int getBulletSize() {
			return (int) (getShooter().getWidth() * random(0.5f, 0.8f));
		}
	}
}
