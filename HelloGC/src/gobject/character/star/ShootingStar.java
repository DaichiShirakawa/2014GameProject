package gobject.character.star;

import static common.Commons.*;

import java.awt.Color;

import texture.TextureLoader;
import gobject.character.GameCharacterImpl;
import gobject.character.MoveMode;

public class ShootingStar extends GameCharacterImpl {
	private static final int SIZE = 10;
	private static final String IMAGE_PATH = IMAGE_FOLDER_STRING
			+ "dotTokiIcon.png";

	public ShootingStar() {
		setTexture(new TextureLoader().loadTexture(IMAGE_PATH));
		setX(WIDTH);
		setY(HEIGHT);
		setVx(-3);
		setVy(-2);
		setWidth(SIZE);
		setHeight(SIZE);
		setMoveModeX(MoveMode.DISPOSE_WITH_FADEOUT);
		setMoveModeY(MoveMode.DISPOSE_WITH_FADEOUT);
		setColor(Color.yellow);
	}
}
