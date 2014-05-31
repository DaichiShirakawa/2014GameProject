package gobject.character.test;

import static common.Commons.*;
import gobject.character.GameCharacterImpl;

import java.awt.Color;

import texture.TextureLoader;

import common.Commons.KEY;

public class DotTest extends GameCharacterImpl {
	public DotTest() {
		setTexture(new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
				+ "dotTokiIcon.png"));
		setX(CENTER_X);
		setY(CENTER_Y);
		setWidth(getTexture().getWidth());
		setHeight(getTexture().getHeight());
		setColor(Color.red);
	}

	@Override
	protected void inputProcess() {
		if (KEYBOARD.isPressed(KEY.UP)) {
			setScale(getScale() * 2);
		}
		if (KEYBOARD.isPressed(KEY.DOWN)) {
			setScale(getScale() / 2);
		}
		if (KEYBOARD.isPressed(KEY.LEFT)) {
			setAngle(getAngle() + 5f);
		}
		if (KEYBOARD.isPressed(KEY.RIGHT)) {
			setAngle(getAngle() - 5f);
		}
	}
}