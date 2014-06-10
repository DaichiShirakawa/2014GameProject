package shirakawa.game.classes.character;

import static shirakawa.game.common.Commons.*;
import shirakawa.game.texture.TextureLoader;

/**
 * 生成された時点のスクリーンショットテクスチャをもつキャラクター
 * 
 * @author shirakawa
 *
 */
public class ScreenShotCharacter extends GameCharacterImpl{

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