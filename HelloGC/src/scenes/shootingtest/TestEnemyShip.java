package scenes.shootingtest;

import static common.CommonMethod.*;
import static common.Commons.*;
import static java.lang.Math.*;

import java.awt.Color;

import scenes.edf.weapons.BasicEffect;
import texture.Texture;
import texture.TextureLoader;
import classes.character.GameCharacterMoveMode;
import classes.character.shooting.ShootingCharacter;
import classes.character.shooting.ShootingCharacterImpl;
import classes.scene.ShootingScene;

public class TestEnemyShip extends ShootingCharacterImpl {
	private int size = 32;
	private float thita = 0;
	private int remainDamageFrame = 0;
	private float dmgVibMove = 0;
	private float dmgVibThita = 0;

	public TestEnemyShip(ShootingScene scene) {
		super(scene, 1);
		setTeam(SHOOTING_TEAM.ENEMY_TEAM);
		setTexture(TextureLoader.loadTexture(IMAGE_FOLDER_STRING
				+ "DotTokiIcon.png"));
		setWidth(size);
		setHeight(size);
		setX(CENTER_X);
		setY(HEIGHT - getHeight());
		setColor(new Color(1f, 0.6f, 0.6f));
		setMoveModeX(GameCharacterMoveMode.LOOP);
		setMoveModeY(GameCharacterMoveMode.LOOP);
	}

	@Override
	public boolean updateProcess() {
		thita += 1 / (float) FPS;
		setX(CENTER_X + 100 * (float) sin(thita));
		setY(CENTER_Y + 100 * (float) cos(thita));
		damageUpdate();
		return super.updateProcess();
	}

	@Override
	public float damage(float damage) {
		remainDamageFrame = 30;
		dmgVibMove = 5;
		dmgVibThita = 0;
		return super.damage(damage);
	}

	public void damageUpdate() {
		remainDamageFrame--;
		if (remainDamageFrame <= 0) {
			return;
		}
		
		dmgVibMove *= 0.95f;
		dmgVibThita += 1;
		setX(getX() + dmgVibMove * (float) sin(dmgVibThita));
		
		if (remainDamageFrame % 10 == 0) {
			getParentScene().add(new Effect(getParentScene(), this));
		}
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
