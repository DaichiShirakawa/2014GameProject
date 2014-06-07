package scenes.shootingtest;

import static common.CommonMethod.*;
import static common.Commons.*;
import scenes.edf.weapons.BasicEffect;
import texture.Texture;
import texture.TextureLoader;
import classes.character.shooting.ShootingBulletCharacter;
import classes.character.shooting.ShootingCharacter;
import classes.character.shooting.ShootingCharacterImpl;
import classes.scene.ShootingScene;

public class TestBullet extends ShootingBulletCharacter {
	private static final int BULLET_POWER = 1;
	private static final int BULLET_RANGE = 200;
	private static final int BULLET_SIZE = 10;
	private static final Texture TEXTURE = TextureLoader.loadTexture(IMAGE_FOLDER_STRING
			+ "flower.png");
	private float ySpeed = 5;

	public TestBullet(ShootingScene parentScene, ShootingCharacterImpl shooter) {
		super(parentScene, shooter, BULLET_POWER);

		setColor(generateCosmosColor());
		setVx(random(-0.5f, 0.5f));
		setVy(ySpeed);
		setVAngle(12);
	}

	@Override
	public void hitEffectTo(ShootingCharacter target) {
		super.hitEffectTo(target);
		this.setTarget(target);
		shoot(new Effect(getParentScene(), target));
	}

	@Override
	public float damage(float damage) {
		for (int i = 0; i < 3; i++) {
			shoot(new BasicEffect(getParentScene(), this));
		}
		return super.damage(damage);
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

	private class Effect extends BasicEffect {
		protected Effect(ShootingScene parentScene, ShootingCharacter shootor) {
			super(parentScene, shootor);
		}

		@Override
		public boolean updateProcess() {
			setVx(getVX() * 0.95f);
			setVy(getVY() * 0.95f);
			return super.updateProcess();
		}

		@Override
		public int getBulletSize() {
			return (int) (getShooter().getWidth() * random(0.2f, 0.3f));
		}

		@Override
		public Texture getBulletTexture() {
			return getShooter().getTexture();
		}

		@Override
		protected float getLifeTime() {
			return 0.8f * random(0.5f, 1.5f);
		}
	}

}
