package scenes.solarsystem;

import static common.Commons.*;

import java.awt.Color;

import classes.character.GameCharacterObjectImpl;
import classes.character.GameCharacterMoveMode;
import texture.TextureLoader;

public class ShootingStarCharacter extends GameCharacterObjectImpl {
	private static final int SIZE = 10;
	private static final String IMAGE_PATH = IMAGE_FOLDER_STRING
			+ "dotTokiIcon.png";

	public ShootingStarCharacter() {
		setTexture(TextureLoader.loadTexture(IMAGE_PATH));
		setX(WIDTH);
		setY(HEIGHT);
		setVx(-3);
		setVy(-2);
		setWidth(SIZE);
		setHeight(SIZE);
		setMoveModeX(GameCharacterMoveMode.DISPOSE_WITH_FADEOUT);
		setMoveModeY(GameCharacterMoveMode.DISPOSE_WITH_FADEOUT);
		setColor(Color.yellow);
	}
}
