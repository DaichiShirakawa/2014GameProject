package gobject.scene.text;

import gobject.character.test.DotTest;
import gobject.character.test.TextTest;
import gobject.scene.GameSceneImpl;

import common.CommonMethod.BackGroundColor;

public class TestScene extends GameSceneImpl {
	public TestScene() {
		BackGroundColor.WHITE.set();

		add(new DotTest());
		add(new TextTest());
	}
}
