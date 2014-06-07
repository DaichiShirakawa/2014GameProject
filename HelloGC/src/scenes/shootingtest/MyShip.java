package scenes.shootingtest;

import static common.Commons.*;
import io.Key;

import java.awt.Color;

import texture.TextureLoader;
import classes.character.GameCharacterMoveMode;
import classes.character.shooting.ShootingCharacter;
import classes.scene.ShootingScene;

public class MyShip extends ShootingCharacter {
	private float speed = 2.5f;
	private int size = 32;

	public MyShip(ShootingScene scene) {
		super(scene, 0);
		setTeam(TEAM.FRIEND_TEAM);
		setTexture(TextureLoader.loadTexture(IMAGE_FOLDER_STRING
				+ "tokiIcon.png"));
		setWidth(size);
		setHeight(size);
		setX(CENTER_X);
		setY(getHeight());
		setColor(new Color(0.6f, 0.6f, 1f));
		setMoveModeX(GameCharacterMoveMode.LOOP);
		setMoveModeY(GameCharacterMoveMode.LOOP);
	}

	@Override
	public boolean updateProcess() {
		setVy(0);
		setVx(0);
		if (Key.UP.isPressing()) {
			setVy(speed);
		}
		if (Key.DOWN.isPressing()) {
			setVy(-speed);
		}
		if (Key.LEFT.isPressing()) {
			setVx(-speed);
		}
		if (Key.RIGHT.isPressing()) {
			setVx(speed);
		}
		if (Key.SPACE.getPressingFrameCount() % 5 == 0) {
			shoot(new TestBullet(getParentScene(), this));
		}
		return super.updateProcess();
	}

}
