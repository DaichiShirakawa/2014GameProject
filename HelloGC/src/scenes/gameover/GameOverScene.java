package scenes.gameover;

import static common.Commons.*;
import io.Key;

import java.awt.Color;

import main.GameSceneManager;
import classes.character.ScreenShotCharacter;
import classes.character.TextCharacter;
import classes.scene.GameScene;
import classes.scene.GameScene;

public class GameOverScene extends GameScene {

	public GameOverScene(GameScene gameoverScene) {
		add(new ScreenShotCharacter());
		gameoverScene.dispose();

		add(new TextCharacter("-GAME OVER-")).setX(CENTER_X)
				.setY(CENTER_Y + 70)
				.setColor(Color.red)
				.setScale(0.5f);
		add(new TextCharacter("press enter to reset")).setX(CENTER_X)
		.setY(CENTER_Y + 50)
		.setColor(Color.red)
		.setScale(0.3f);
	}

	@Override
	public void inputProcess() {
		if (Key.RETURN.isPressed()) {
			GameSceneManager.getInstance()
					.reset();
		}
	}
}
