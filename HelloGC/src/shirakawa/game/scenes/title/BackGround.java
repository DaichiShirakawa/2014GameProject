package shirakawa.game.scenes.title;

import static shirakawa.game.common.Commons.*;
import shirakawa.game.classes.character.GameCharacterImpl;
import shirakawa.game.texture.TextureLoader;

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
