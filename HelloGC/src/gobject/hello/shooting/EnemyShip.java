package gobject.hello.shooting;

import static common.CommonLogic.*;
import static common.Commons.*;

import java.awt.Color;

import texture.TextureLoader;

public class EnemyShip extends GStgCharacter{
	private Color color_;
	private int size_ = 32;
	private int speed_ = 1;

	public EnemyShip() {
		setDivision(DIVISION.ENEMY);
		setTexture(new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
				+ "DotTokiIcon.png"));
		setWidth(size_);
		setHeight(size_);
		setX(WIDTH / 2);
		setY(HEIGHT - getHeight());
		color_ = new Color(1f, 0.6f, 0.6f);
		setVx(speed_);
		setVy(speed_);
		setXMoveMode(MOVEMODE.LOOP);
		setYMoveMode(MOVEMODE.LOOP);
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public void render() {
		setGlColor4f(color_, getAlpha());
		draw();
	}

}
