package scenes.shootingtest;

import static common.Commons.*;
import static java.lang.Math.*;

import java.awt.Color;

import texture.TextureLoader;
import classes.character.GameCharacterMoveMode;
import classes.character.shooting.ShootingCharacter;
import classes.scene.ShootingScene;

public class TestEnemyShip extends ShootingCharacter {
	private int size = 32;
	private float thita = 0;
	private boolean damaging = false;
	private float dmgVibMove = 0;
	private float dmgVibThita = 0;

	public TestEnemyShip(ShootingScene scene) {
		super(scene, 1);
		setTeam(TEAM.ENEMY_TEAM);
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
	public void update() {
		thita += 1 / (float) FPS;
		setX(CENTER_X + 100 * (float) sin(thita));
		setY(CENTER_Y + 100 * (float) cos(thita));
		damageUpdate();
		super.update();
	}

	@Override
	public float damage(float damage) {
		damaging = true;
		dmgVibMove = 5;
		dmgVibThita = 0;
		return super.damage(damage);
	}

	public void damageUpdate() {
		if (!damaging) {
			return;
		}
		dmgVibMove *= 0.95f;
		dmgVibThita += 1;
		setX(getPixcelX() + dmgVibMove * (float) sin(dmgVibThita));
		if (dmgVibMove < 0.5) {
			damaging = false;
		}
	}
}
