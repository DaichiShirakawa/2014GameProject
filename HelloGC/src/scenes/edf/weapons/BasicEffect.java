package scenes.edf.weapons;

import static common.CommonMethod.*;
import static common.Commons.*;
import static java.lang.Math.*;
import texture.Texture;
import classes.character.shooting.ShootingEffectCharacter;
import classes.character.shooting.ShootingObject;
import classes.scene.ShootingScene;

public class BasicEffect extends ShootingEffectCharacter {
	public BasicEffect(ShootingScene parentScene, ShootingObject source) {
		super(parentScene, source);
		setScale(random(0.5f, 2f));
		setColor(source.getColor());

		float tmp = RANDOM.nextInt(360);
		setVx(3 * (float) sin(tmp) * random(0.2f, 1f));
		setVy(3 * (float) cos(tmp) * random(0.2f, 1f));
		setAngle(tmp);
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
	protected float getLifeTime() {
		return 0.8f * random(0.5f, 1.5f);
	}

	@Override
	public boolean updateProcess() {
		setVx(getVX() * 0.95f);
		setVy(getVY() * 0.95f);
		return super.updateProcess();
	}

}