package scenes.title;

import static common.Commons.*;
import texture.TextureLoader;
import classes.character.GameCharacterImpl;

/**
 * タイトルの背景
 * 
 * @author shirakawa
 *
 */
class BackGround extends GameCharacterImpl {
	public BackGround() {
		setTexture(TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING
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
