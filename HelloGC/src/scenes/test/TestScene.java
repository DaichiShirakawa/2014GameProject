package scenes.test;

import classes.scene.GameSceneImpl;
import common.CommonMethod.BackGroundColor;

public class TestScene extends GameSceneImpl {
	public TestScene() {
		BackGroundColor.WHITE.set();

		add(new DotTestCharacter());
		add(new TextTestCharacter());
	}
}
