package scenes.edf.gui;

import static common.Commons.*;

import java.awt.Color;

import scenes.edf.EDFScene;
import classes.character.GameCharacterBasePoint;
import classes.character.TextCharacter;

public class EDFMoneyCaption extends TextCharacter {
	EDFScene scene;

	public EDFMoneyCaption(EDFScene edfScene) {
		super(getCaption(edfScene.getMoney()));
		this.scene = edfScene;
		setBasePont(GameCharacterBasePoint.LEFTTOP);
		setScale(0.4f);
		setX(5);
		setY(HEIGHT - 5);
		setColor(Color.orange.brighter());
	}

	private static String getCaption(int money) {
		return "$ " + money;
	}

	@Override
	public void update() {
		super.update();
		updateText(getCaption(scene.getMoney()));
	}
}