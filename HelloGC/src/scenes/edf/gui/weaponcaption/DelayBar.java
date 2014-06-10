package scenes.edf.gui.weaponcaption;

import java.util.ArrayList;
import java.util.List;

import texture.Texture;
import texture.text.TextTextureMaker;
import classes.character.GameCharacterBasePoint;
import classes.character.TextCharacter;

/**
 * 武器の射撃ディレイを可視化するGUI
 * 
 * @author shirakawa
 * 
 */
class DelayBar extends TextCharacter {
	//HACK テクスチャをいっぱい用意するイケてない方法。
	private static final List<Texture> textureList = new ArrayList<>();
	static {
		textureList.add(null);
		textureList.add(TextTextureMaker.createText("■"));
		textureList.add(TextTextureMaker.createText("■■"));
		textureList.add(TextTextureMaker.createText("■■■"));
		textureList.add(TextTextureMaker.createText("■■■■"));
		textureList.add(TextTextureMaker.createText("■■■■■"));
		textureList.add(TextTextureMaker.createText("■■■■■■"));
		textureList.add(TextTextureMaker.createText("■■■■■■■"));
		textureList.add(TextTextureMaker.createText("■■■■■■■■"));
		textureList.add(TextTextureMaker.createText("■■■■■■■■■"));
		textureList.add(TextTextureMaker.createText("■■■■■■■■■■"));
	}

	private OddWeaponGUI parent;

	public DelayBar(OddWeaponGUI parent) {
		super();
		this.parent = parent;
		setBasePont(GameCharacterBasePoint.LEFTBOTTOM);
		setX(parent.getX() - 24);
		setY(parent.getY() - 20);
		setHeight(10);
	}

	@Override
	protected boolean updateProcess() {
		int remain = (int) ((float) parent.getWeapon()
				.getRemainDelayFrame() / (float) parent.getWeapon()
				.getMaxDelayFrame() * 10);
		if (remain <= 0 || textureList.size() <= remain) {
			hide();
		} else {
			show();
			setTexture(textureList.get(remain));
			setWidth(remain * 5);
		}
		return super.updateProcess();
	}
}
