package scenes.shootingtest;

import static common.Commons.*;
import io.Key;

import java.awt.Color;

import classes.character.GameCharacterMoveMode;
import classes.character.ShootingCharacter;
import classes.scene.ShootingScene;
import texture.TextureLoader;

public class MyShip extends ShootingCharacter {
	private float speed = 2.5f;
	private int size = 32;

	public MyShip(ShootingScene scene) {
		super(scene);
		setDivision(DIVISION.FRIENDLY);
		setTexture(new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
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
	public void update() {
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
		super.update();
	}

}
