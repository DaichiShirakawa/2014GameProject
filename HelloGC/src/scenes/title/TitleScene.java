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
		pressStartText = add(new TextCharacter("PRESS START!"));
		pressStartText.setColor(Color.blue)
				.setX(CENTER_X)
				.setY(CENTER_Y)
				.setScale(0.5f);
	}

	@Override
	public void update() {
		super.update();
		 if (FPSManager.totalFrame() % 45 == 0) {
		 pressStartText.toggleVisible();
		 }

		if (!Key.ESCAPE.isPressed() && Key.anyKeyPressed()) {
			GameSceneManager.getInstance()
					.changeSceneIfNotNull(nextScene);
		}
	}
	
	@Override
	public void render() {
		super.render();
	}
}