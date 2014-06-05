package classes.character;

import static common.Commons.*;
import texture.TextureLoader;

public class ScreenShotCharacter extends SimpleCharacter {

	public ScreenShotCharacter() {
		setTexture(TextureLoader.getScreenShot());
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setX(CENTER_X);
		setY(CENTER_Y);
	}

	@Override
	public void dispose() {
		getTexture().dispose();
	}
}