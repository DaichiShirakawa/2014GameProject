package gobject.character.test;

import static common.Commons.*;
import gobject.character.GameCharacterImpl;
import io.Key;

import java.awt.Color;

import texture.TextureLoader;

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
		if (Key.UP.isPressed()) {
			setScale(getScale() * 2);
		}
		if (Key.DOWN.isPressed()) {
			setScale(getScale() / 2);
		}
		if (Key.LEFT.isPressed()) {
			setAngle(getAngle() + 5f);
		}
		if (Key.RIGHT.isPressed()) {
			setAngle(getAngle() - 5f);
		}
	}
}