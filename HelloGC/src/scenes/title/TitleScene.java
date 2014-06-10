package scenes.title;

import static common.Commons.*;
import io.Key;

import java.awt.Color;

import main.FPSManager;
import main.GameSceneManager;
import main.GameSceneManager.SceneCollection;
import classes.character.TextCharacter;
import classes.scene.GameScene;

import common.CommonMethod.BackGroundColor;

/**
 * 起動時に表示されるおなじみの画面
 * 
 * PRESS START!
 * 
 * @author shirakawa
 * 
 */
public class TitleScene extends GameScene {
	private static final SceneCollection nextScene = SceneCollection.EDF;
	private TextCharacter pressStartText;

	public TitleScene() {
		BackGroundColor.WHITE.set();
		
		add(new BackGround());
		
		pressStartText = add(new TextCharacter("PRESS RETURN KEY!"));
		pressStartText.setColor(Color.white)
				.setX(CENTER_X)
				.setY(CENTER_Y)
				.setScale(0.5f);
	}

	@Override
	public boolean updateProcess() {
		if (FPSManager.totalFrame() % 45 == 0) {
			pressStartText.toggleVisible();
		}

		if (Key.RETURN.isPressed()) {
			GameSceneManager.getInstance()
					.changeSceneIfNotNull(nextScene);
		}
		return false;
	}
}