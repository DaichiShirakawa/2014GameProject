package gobject.hello.shooting;

import static common.CommonLogic.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.GameObject;
import gobject.hello.shooting.bullet.NormalBullet;

import java.awt.Color;

import texture.TextureLoader;

public class MyShip extends GStgCharacter{
	private Color color_;
	private float speed_ = 2;
	private int size_ = 32;

	public MyShip() {
		division_ = DIV_FRIENDLY;
		setTexture(new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
				+ "tokiIcon.png"));
		setWidth(size_);
		setHeight(size_);
		setX(WIDTH / 2);
		setY(getHeight());
		color_ = new Color(0.6f, 0.6f, 1f);
		setMoveMode(MOVEMODE.LIMITED_DISPLAY);
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
		move();
	}

	@Override
	public void render() {
		glLoadIdentity();
		setGlColor4f(color_, getAlpha());
		draw();
	}

}
