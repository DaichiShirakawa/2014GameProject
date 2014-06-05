package scenes.test;

import classes.scene.GameScene;
import common.CommonMethod.BackGroundColor;

public class TestScene extends GameScene {
	public TestScene() {
		BackGroundColor.WHITE.set();

		add(new DotTestCharacter());
		add(new TextTestCharacter());
	}
}
