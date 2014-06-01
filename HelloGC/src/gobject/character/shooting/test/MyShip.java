package gobject.character.shooting.test;

import static common.Commons.*;
import gobject.character.MoveMode;
import gobject.character.shooting.ShootingCharacter;
import gobject.character.shooting.bullets.NormalBullet;
import gobject.scene.shooting.ShootingScene;
import io.Key;

import java.awt.Color;

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
		setMoveModeX(MoveMode.LOOP);
		setMoveModeY(MoveMode.LOOP);
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
			shoot(new NormalBullet(this));
		}
		super.update();
	}

}
