package classes.character;

import static common.Commons.*;
import texture.TextureLoader;

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