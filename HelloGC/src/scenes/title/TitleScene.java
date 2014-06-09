package scenes.title;

import static common.Commons.*;
import io.Key;

import java.awt.Color;

import main.FPSManager;
import main.GameSceneManager;
import main.GameSceneManager.SceneCollection;
import classes.character.GameCharacterBasePoint;
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
		pressStartText = add(new TextCharacter("PRESS RETURN KEY!"));
		pressStartText.setColor(Color.blue)
				.setX(CENTER_X)
				.setY(CENTER_Y)
				.setScale(0.5f);

		add(new TextCharacter("F1~F4: テストシーン\n" 
							+ "ESC: ゲームリセット")).setScale(0.3f)
				.setBasePont(GameCharacterBasePoint.LEFTBOTTOM)
				.setX(5)
				.setY(5)
				.setColor(Color.black);
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