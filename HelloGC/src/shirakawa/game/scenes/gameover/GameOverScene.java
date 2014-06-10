package shirakawa.game.scenes.gameover;

import static shirakawa.game.common.Commons.*;

import java.awt.Color;

import shirakawa.game.classes.character.ScreenShotCharacter;
import shirakawa.game.classes.character.TextCharacter;
import shirakawa.game.classes.scene.GameScene;
import shirakawa.game.io.Key;
import shirakawa.game.main.FPSManager;
import shirakawa.game.main.GameSceneManager;

/**
 * 呼ばれた時点のスクリーンショットを撮って「GAME OVER」メッセージを表示するシーン。
 * 
 * @author shirakawa
 * 
 */
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
	public boolean inputProcess() {
		if (Key.RETURN.isPressed()) {
			GameSceneManager.getInstance()
					.gameReset();
		}
		return super.inputProcess();
	}

	@Override
	protected boolean updateProcess() {
		if (FPSManager.totalFrame() % 60 == 0) {
			gameoverText.toggleVisible();
		}
		return true;
	}
}
