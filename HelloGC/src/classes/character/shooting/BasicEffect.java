package classes.character.shooting;

import static common.CommonMethod.*;
import static common.Commons.*;
import static java.lang.Math.*;
import classes.scene.ShootingScene;
import texture.Texture;

public class BasicEffect extends ShootingEffectCharacter {
	public BasicEffect(ShootingScene parentScene, ShootingCharacter source) {
		super(parentScene, source);
		setScale(random(0.5f, 2f));
		setColor(source.getColor());

		setAngle(RANDOM.nextInt(360));
		setMoveSpeeds();
	}

	protected void setMoveSpeeds() {
		float tmp = RANDOM.nextInt(360);
		setVX(3 * (float) sin(tmp) * random(0.2f, 1f));
		setVY(3 * (float) cos(tmp) * random(0.2f, 1f));
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
		setVX(getVX() * 0.95f);
		setVY(getVY() * 0.95f);
		return super.updateProcess();
	}

}