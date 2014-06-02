package scenes.shootingtest;

import static common.CommonMethod.*;
import static common.Commons.*;
import static java.lang.Math.*;
import texture.Texture;
import texture.TextureLoader;
import classes.character.ShootingCharacter;
import classes.scene.ShootingScene;

public class TestBullet extends ShootingBulletCharacterImpl {
	private static final Texture TEXTURE = new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
			+ "flower.png");
	private float ySpeed = 5;

	public TestBullet(ShootingScene parentScene, ShootingCharacter shooter) {
		super(parentScene, shooter);

		setColor(generateCosmosColor());
		setVx(random(-0.5f, 0.5f));
		setVy(ySpeed);
		setVAngle(12);
	}

	@Override
	public void update() {
		super.update();
		if (!isEnable()) {
			if (getTarget() != null && getDisposeTimer() % (FPS / 4) == 0) {
				shoot(new Effect(getParentScene(), getTarget()));
			}
			return;
		}
	}

	@Override
	public void hitEffect(ShootingCharacter target) {
		super.hitEffect(target);
		for (int i = 0; i < 3; i++) {
			shoot(new Effect(getParentScene(), this));
		}
		disposeAfter(0.5f);
		disable();
		this.setTarget(target);
		shoot(new Effect(getParentScene(), target));
	}

	@Override
	public float getBulletRange() {
		return 200;
	}

	@Override
	public int getBulletSize() {
		return 10;
	}

	@Override
	public Texture getBulletTexture() {
		return TEXTURE;
	}

	@Override
	public float getPower() {
		return 1;
	}

	private class Effect extends ShootingBulletCharacterImpl {
		protected Effect(ShootingScene parentScene, ShootingCharacter shootor) {
			super(parentScene, shootor);
			setScale(random(0.5f, 2f));
			setColor(shootor.getColor());

			float tmp = RANDOM.nextInt(360);
			setVx(3 * (float) sin(tmp) * random(0.2f, 1f));
			setVy(3 * (float) cos(tmp) * random(0.2f, 1f));
			setAngle(tmp);
			disposeAfter(0.8f * random(0.5f, 1.5f));
		}

		@Override
		public void update() {
			setVx(getVx() * 0.95f);
			setVy(getVy() * 0.95f);
			super.update();
		}

		@Override
		public void render() {
			setGlColor4f(getColor(), getAlpha());
			super.render();
		}

		@Override
		public void hitEffect(ShootingCharacter target) {
			return;
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
		public float getPower() {
			return 0;
		}
	}

}
