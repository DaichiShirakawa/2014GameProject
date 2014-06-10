package scenes.tokishooting.weapons.bomb;

import static common.Commons.*;
import main.FPSManager;
import scenes.tokishooting.TokiShootingScene;
import scenes.tokishooting.weapons.TSBulletBase;
import texture.Texture;
import texture.TextureLoader;
import classes.character.shooting.ShootingCharacter;

/**
 * ボム弾
 * 
 * @author shirakawa
 *
 */
class BombBullet extends TSBulletBase {
	private static final int BULLET_POWER = 0;
	private static final int BULLET_HP = 1;
	private static final int BULLET_SIZE = 20;
	private static final int BULLET_RANGE = 400;
	private static final Texture TEXTURE = TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING + "bullet-suika.png");
	private static final float SPEED = 0.7f;

	public BombBullet(TokiShootingScene parentScene, ShootingCharacter shooter) {
		super(parentScene, shooter, BULLET_POWER, BULLET_HP);

		double theta = Math.toRadians(-getShooter().getAngle());
		setVX(SPEED * (float) Math.sin(theta));
		setVY(SPEED * (float) Math.cos(theta));
		setVAngle(1);
	}

	@Override
	public boolean updateProcess() {
		if (FPSManager.totalFrame() % 15 == 0) {
			getParentScene().add(new SmokeEffect(getParentScene(), this, true));
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
		for (int i = 0; i < 20; i++) {
			shoot(new BombChildBullet(getParentScene(), this)).booking(
					RANDOM.nextInt(60));
		}
	}
}
