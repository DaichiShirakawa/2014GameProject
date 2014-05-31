package gobject.scene.texttest;

import static common.Commons.*;
import gobject.GameObject;
import gobject.character.BasePoint;
import gobject.character.GameCharacter;
import gobject.character.text.TextCharacter;
import gobject.scene.GameSceneImpl;

import java.awt.Color;

import main.FPSManager;
import common.CommonMethod.BackGroundColor;

public class TextTestScene extends GameSceneImpl {

	private TextCharacter testText;

	public TextTestScene() {
		BackGroundColor.WHITE.set();

		testText = add(new MyTextCharacter(getTestText()).setBasePont(
				BasePoint.LEFTTOP)
				.setColor(Color.green)
				.setX(5)
				.setY(HEIGHT - 5));
	}
	
	@Override
	public void update() {
		testText.
		super.update();
	}

	private String getTestText() {
		return "てすtes" + FPSManager.getFramesUntilStart();
	}

	private class MyTextCharacter extends TextCharacter {
		public MyTextCharacter(String text) {
			super(text);
		}

		@Override
		public void update() {
			updateText(getTestText());
		}
	}

}
