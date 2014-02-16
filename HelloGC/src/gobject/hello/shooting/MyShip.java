package gobject.hello.shooting;

import static common.CommonLogic.*;
import static common.Commons.*;
import gobject.hello.shooting.bullet.NormalBullet;

import java.awt.Color;

import texture.TextureLoader;

public class MyShip extends GStgCharacter{
	private Color color_;
	private float speed_ = 2.5f;
	private int size_ = 32;

	public MyShip() {
		setDivision(DIVISION.FRIENDLY);
		setTexture(new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
				+ "tokiIcon.png"));
		setWidth(size_);
		setHeight(size_);
		setX(WIDTH / 2);
		setY(getHeight());
		color_ = new Color(0.6f, 0.6f, 1f);
		setXMoveMode(MOVEMODE.LOOP);
		setYMoveMode(MOVEMODE.LOOP);
	}

	@Override
	public void update() {
		setVy(0);
		setVx(0);
		if (keyboard.isPress(KEY_UP)) {
			setVy(speed_);
		}
		if (keyboard.isPress(KEY_DOWN)) {
			setVy(-speed_);
		}
		if (keyboard.isPress(KEY_LEFT)) {
			setVx(-speed_);
		}
		if (keyboard.isPress(KEY_RIGHT)) {
			setVx(speed_);
		}
		if (keyboard.getPressLength(KEY_Z) % 5 == 0) {
			ShootingLogic.GetInstance().shoot(new NormalBullet(this)); 
		}
		super.update();
	}

	@Override
	public void render() {
		setGlColor4f(color_, getAlpha());
		draw();
	}


}
