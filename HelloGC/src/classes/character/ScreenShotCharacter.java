package classes.character;

import static common.Commons.*;
import texture.TextureLoader;

public class ScreenShotCharacter extends GameCharacterObjectImpl{

	public ScreenShotCharacter() {
		setTexture(TextureLoader.getScreenShot());
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setX(CENTER_X);
		setY(CENTER_Y);
	}

	@Override
	protected boolean canDisposeTexture() {
		return true;
	}
}