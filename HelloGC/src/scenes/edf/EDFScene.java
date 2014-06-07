package scenes.edf;

import io.Key;
import main.GameSceneManager;
import scenes.edf.gui.EDFMoneyCaption;
import scenes.edf.gui.EDFWeaponCaption;
import classes.scene.ShootingScene;

import common.CommonMethod.BackGroundColor;

public class EDFScene extends ShootingScene {
	private int money = 0;
	private EDFMainCharacterController mainCharacters;
	private EDFStageController stages;

	public EDFScene() {
		BackGroundColor.BLACK.set();
		mainCharacters = add(new EDFMainCharacterController());
		stages = add(new EDFStageController(this));
		add(new EDFMoneyCaption(this));
		add(new EDFWeaponCaption(mainCharacters));
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
		if (checkPause()) {
			stages.update();
			return false;
		}
		if (checkGameover()) {
			GameSceneManager.getInstance()
					.gameover();
			return false;
		}
		return super.updateProcess();
	}

	private boolean checkPause() {
		return stages.isPausing();
	}

	private boolean checkGameover() {
		return !(mainCharacters.earthArrive());
	}

	@Override
	public void inputProcess() {
		/**
		 * TODO ゲームオーバーテスト用。提出時削除すべし
		 */
		if (Key.O.isPressed()) {
			GameSceneManager.getInstance()
					.gameover();
		}
	}

}
