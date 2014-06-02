package scenes.test;

import static common.Commons.*;

import java.awt.Color;

import classes.character.GameCharacterBasePoint;
import classes.character.TextCharacter;
import main.FPSManager;

public class TextTestCharacter extends TextCharacter {

	public TextTestCharacter() {
		super(getTestText());
		this.setBasePont(GameCharacterBasePoint.LEFTTOP)
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
		return "てすtes" + FPSManager.totalFrame();
	}

}
