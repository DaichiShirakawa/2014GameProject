package gobject.scene.texttest;

import static common.Commons.*;
import gobject.character.GameCharacterImpl;
import gobject.scene.GameSceneImpl;

import java.awt.Color;

import main.FPSManager;
import texture.text.FontDef;
import texture.text.TextTextureMaker;

import common.CommonMethod.BackGroundColor;

public class TextTestScene extends GameSceneImpl {

	public TextTestScene() {
		BackGroundColor.WHITE.set();
		add(new TestClass());
	}

	private class TestClass extends GameCharacterImpl {
		public TestClass() {
			setX(CENTER_X);
			setY(CENTER_Y);
			setTexture(TextTextureMaker.createText("てすと", Color.red,
					FontDef.RICTY_24));
			setWidth(getTexture().getWidth());
			setHeight(getTexture().getHeight());
		}

		@Override
		public void update() {
			getTexture().dispose();
			setTexture(TextTextureMaker.createText(
					"ときtoki" + FPSManager.getFramesUntilStart(), Color.red));
			setWidth(getTexture().getWidth());
			setHeight(getTexture().getHeight());
		}
	}

}
