package scenes.tokishooting.gui.moneycaption;

import static common.Commons.*;

import java.awt.Color;

import scenes.tokishooting.TSScene;
import classes.character.TextCharacter;

public class TSMoneyCaption extends TextCharacter {
	TSScene scene;

	public TSMoneyCaption(TSScene edfScene) {
		super(getCaption(edfScene.getMoney()));
		this.scene = edfScene;
		setScale(0.4f);
		setX(CENTER_X);
		setY(30);
		setColor(Color.orange.brighter());
	}

	private static String getCaption(int money) {
		return "$ " + money;
	}

	@Override
	protected boolean updateProcess() {
		updateText(getCaption(scene.getMoney()));
		return super.updateProcess();
	}

}