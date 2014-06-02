package scenes.edf.gui;

import static common.Commons.*;

import java.awt.Color;

import scenes.edf.EDFScene;
import classes.character.GameCharacterBasePoint;
import classes.character.TextCharacter;


public class MoneyCaption extends TextCharacter {
	EDFScene scene;
	int currentMoney;

	public MoneyCaption(EDFScene parentScene) {
		super(getCaption(parentScene.getMoney()));
		this.scene = parentScene;
		currentMoney = parentScene.getMoney();
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
		if (scene.getMoney() != currentMoney) {
			currentMoney = scene.getMoney();
			updateText(getCaption(currentMoney));
		}
	}
}