package scenes.edf.weapons;

import static common.CommonMethod.*;
import static common.Commons.*;
import static java.lang.Math.*;
import scenes.shootingtest.ShootingBulletCharacterImpl;
import texture.Texture;
import classes.character.ShootingCharacter;
import classes.scene.ShootingScene;

public class BasicEffect extends ShootingBulletCharacterImpl {
	public BasicEffect(ShootingScene parentScene, ShootingCharacter shooter) {
		super(parentScene, shooter);
		setScale(random(0.5f, 2f));
		setColor(shooter.getColor());

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
		return (int) (getShooter().getWidth() * random(0.2f, 0.4f));
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