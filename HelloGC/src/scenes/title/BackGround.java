package scenes.title;

import static common.Commons.*;
import texture.TextureLoader;
import classes.character.GameCharacterImpl;

class BackGround extends GameCharacterImpl {
	public BackGround() {
		setTexture(TextureLoader.loadTexture(NAOKO_FOLDER_STRING
				+ "title.png"));
		setX(CENTER_X);
		setY(CENTER_Y);
		setWidth(WIDTH);
		setHeight(HEIGHT);
	}

	@Override
	protected boolean canDisposeTexture() {
		return true;
	}

}
