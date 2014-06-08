package scenes.test;

import java.awt.Color;

import classes.character.GameCharacterBasePoint;
import classes.character.TextCharacter;
import classes.scene.GameScene;
import common.CommonMethod.BackGroundColor;

public class TestScene extends GameScene {
	public TestScene() {
		BackGroundColor.WHITE.set();

		add(new DotTestCharacter());
		add(new TextTestCharacter());

		add(new TextCharacter("input: ↑,↓,→,←")).setScale(0.3f)
		.setBasePont(GameCharacterBasePoint.LEFTBOTTOM)
		.setX(5)
		.setY(5)
		.setColor(Color.black);
	}

	@Override
	protected boolean updateProcess() {
		return true;
	}
}
