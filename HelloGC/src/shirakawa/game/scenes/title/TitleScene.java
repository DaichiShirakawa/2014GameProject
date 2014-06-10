package shirakawa.game.scenes.title;

import static shirakawa.game.common.Commons.*;

import java.awt.Color;

import shirakawa.game.classes.character.TextCharacter;
import shirakawa.game.classes.scene.GameScene;
import shirakawa.game.common.CommonMethod.BackGroundColor;
import shirakawa.game.io.Key;
import shirakawa.game.main.FPSManager;
import shirakawa.game.main.GameSceneManager;
import shirakawa.game.main.GameSceneManager.SceneCollection;

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