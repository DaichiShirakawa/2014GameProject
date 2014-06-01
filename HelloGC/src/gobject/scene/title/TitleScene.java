package gobject.scene.title;

import static common.Commons.*;
import gobject.character.GameCharacter;
import gobject.character.text.TextCharacter;
import gobject.scene.GameSceneImpl;
import gobject.scene.GameSceneManager;
import gobject.scene.GameSceneManager.SceneCollection;
import io.Key;

import java.awt.Color;

import main.FPSManager;

import common.CommonMethod.BackGroundColor;

/**
 * 起動時に表示されるおなじみの画面
 * 
 * PRESS START!
 * 
 * @author shirakawa
 * 
 */
public class TitleScene extends GameSceneImpl {
	private static final SceneCollection nextScene = SceneCollection.EDF;
	private GameCharacter pressStartText;

	public TitleScene() {
		BackGroundColor.WHITE.set();
		pressStartText = new TextCharacter("PRESS START!").setColor(Color.blue)
				.setX(CENTER_X)
				.setY(CENTER_Y);
		add(pressStartText);
	}

	@Override
	public void update() {
		if (FPSManager.getFramesUntilStart() % 45 == 0) {
			pressStartText.toggleVisible();
		}

		if (Key.anyKeyOperating()) {
			GameSceneManager.getInstance()
					.changeSceneIfNotNull(nextScene);
		}
	}
}