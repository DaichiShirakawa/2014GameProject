package gobject.character.text;

import gobject.character.GameCharacterImpl;
import texture.text.FontDef;
import texture.text.TextTextureMaker;

/**
 * テキストテクスチャのみを持つシンプルなキャラクター
 * 
 * @author shirakawa
 * 
 */
public class TextCharacter extends GameCharacterImpl {
	private FontDef fontDef = FontDef.DEFAULT;

	public TextCharacter(String text) {
		setTexture(TextTextureMaker.createText(text));
		resetSize();
	}

	public TextCharacter(String text, FontDef fontDef) {
		this.fontDef = fontDef;
		setTexture(TextTextureMaker.createText(text, fontDef));
		resetSize();
	}

	public void updateText(String text) {
		getTexture().dispose();
		setTexture(TextTextureMaker.createText(text, fontDef));
		resetSize();
	}

	public void resetSize() {
		setWidth(getTexture().getWidth());
		setHeight(getTexture().getHeight());
	}

}
