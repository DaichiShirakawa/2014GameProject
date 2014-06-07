package scenes.gameover;

import static common.Commons.*;
import io.Key;

import java.awt.Color;

import main.FPSManager;
import main.GameSceneManager;
import classes.character.ScreenShotCharacter;
import classes.character.TextCharacter;
import classes.scene.GameScene;

public class GameOverScene extends GameScene {
	TextCharacter gameoverText;

	public GameOverScene(GameScene gameoverScene) {
		add(new ScreenShotCharacter());
		gameoverScene.destroy();

		gameoverText = add(new TextCharacter("-GAME OVER-"));
		gameoverText.setX(CENTER_X)
				.setY(CENTER_Y + 70)
				.setColor(Color.red)
				.setScale(0.5f);

		add(new TextCharacter("press return to reset game")).setX(CENTER_X)
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

	@Override
	protected boolean updateProcess() {
		if (FPSManager.totalFrame() % 60 == 0) {
			gameoverText.toggleVisible();
		}
		return true;
	}
}
