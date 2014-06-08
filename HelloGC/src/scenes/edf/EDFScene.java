package scenes.edf;

import io.Key;
import main.GameSceneManager;
import scenes.edf.gui.EDFMoneyCaption;
import scenes.edf.gui.EDFWeaponCaption;
import scenes.edf.stage.EDFStageController;
import classes.character.shooting.ShootingCharacter;
import classes.scene.ShootingScene;
import common.CommonMethod.BackGroundColor;

public class EDFScene extends ShootingScene {
	private int money = 0;
	private EDFMainCharacterController mainCharacters;
	private EDFStageController stages;
	private boolean pausing = false;

	public EDFScene() {
		BackGroundColor.BLACK.set();
		mainCharacters = add(new EDFMainCharacterController(this));
		stages = add(new EDFStageController(this));
		add(new EDFMoneyCaption(this));
		add(new EDFWeaponCaption(mainCharacters));
	}

	public ShootingCharacter addCharacter(ShootingCharacter character) {
		mainCharacters.add(character);
		return character;
	}

	public int getMoney() {
		return money;
	}

	public int addMoney(int value) {
		money += value;
		return money;
	}

	@Override
	public boolean updateProcess() {
		if (isGameOver()) {
			GameSceneManager.getInstance()
					.gameover();
			return false;
		}
		if (isPausing()) {
			inputProcess();
			return false;
		}
		return super.updateProcess();
	}

	public boolean isPlaying() {
		return stages.isPlaying();
	}

	public boolean isBreakTime() {
		return stages.isBreakTime();
	}

	public boolean isPausing() {
		return pausing;
	}

	public boolean isGameOver() {
		return !(mainCharacters.earthArrive());
	}

	@Override
	public boolean inputProcess() {
		// TODO ゲームオーバーテスト用。提出時削除すべし
		if (Key.O.isPressed()) {
			GameSceneManager.getInstance()
					.gameover();
		}

		// TODO ポーズ中キャプションがほしい
		if (Key.P.isPressed()) {
			if (isPlaying()) {
				pausing = !pausing;
			}
		}
		return super.inputProcess();
	}

}
