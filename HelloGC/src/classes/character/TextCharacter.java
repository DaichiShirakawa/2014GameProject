package classes.character;

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

	public TextCharacter() {
		//
	}

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
		if (getTexture() != null) {
			getTexture().dispose();
		}
		setTexture(TextTextureMaker.createText(text, fontDef));
		resetSize();
	}

	public void resetSize() {
		setWidth(getTexture().getWidth());
		setHeight(getTexture().getHeight());
	}

}
