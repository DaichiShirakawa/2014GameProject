package scenes.title;

import static common.Commons.*;
import io.Key;

import java.awt.Color;

import classes.character.GameCharacter;
import classes.character.TextCharacter;
import classes.scene.GameSceneImpl;
import main.FPSManager;
import main.GameSceneManager;
import main.GameSceneManager.SceneCollection;
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
		if (FPSManager.totalFrame() % 45 == 0) {
			pressStartText.toggleVisible();
		}

		if (!Key.ESCAPE.isPressed() && Key.anyKeyReleased()) {
			GameSceneManager.getInstance()
					.changeSceneIfNotNull(nextScene);
		}
	}
}