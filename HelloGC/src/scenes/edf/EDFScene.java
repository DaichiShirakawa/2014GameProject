package scenes.edf;

import io.Key;
import main.GameSceneManager;
import scenes.edf.gui.EDFMoneyCaption;
import scenes.edf.gui.EDFWeaponCaption;
import classes.GameObject;
import classes.character.shooting.ShootingObject;
import classes.scene.ShootingScene;

import common.CommonMethod.BackGroundColor;

public class EDFScene extends ShootingScene {
	private int money = 0;
	private EDFCharacterController characterController;
	private EDFStageController stageController;

	public EDFScene() {
		BackGroundColor.BLACK.set();
		characterController = add(new EDFCharacterController());
		stageController = add(new EDFStageController(this));
		add(new EDFMoneyCaption(this));
		add(new EDFWeaponCaption(characterController));
	}

	@Override
	public <T extends GameObject> T add(T go) {
		if (go instanceof ShootingObject) {
			return characterController.add(go);
		}
		return super.add(go);
	}

	public int getMoney() {
		return money;
	}

	public int addMoney(int value) {
		money += value;
		return money;
	}

	@Override
	public void update() {
		if (checkPause() || checkGameover()) {
			return;
		}
		super.update();
	}

	private boolean checkPause() {
		return stageController.pausing();
	}

	private boolean checkGameover() {
		if (characterController.earthArrive()) {
			return false;
		}

		GameSceneManager.getInstance()
				.gameover();
		return true;
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
