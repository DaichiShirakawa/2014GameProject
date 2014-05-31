package gobject.character.test;

import static common.Commons.*;
import gobject.character.BasePoint;
import gobject.character.text.TextCharacter;

import java.awt.Color;

import main.FPSManager;

public class TextTest extends TextCharacter {

	public TextTest() {
		super(getTestText());
		this.setBasePont(BasePoint.LEFTTOP)
				.setColor(Color.green.darker())
				.setX(5)
				.setY(HEIGHT - 5);
	}

	@Override
	public void update() {
		updateText(getTestText());
		super.update();
	}

	private static String getTestText() {
		return "てすtes" + FPSManager.getFramesUntilStart();
	}

}
