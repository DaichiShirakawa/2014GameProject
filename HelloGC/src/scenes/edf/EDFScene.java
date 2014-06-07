package scenes.edf;

import io.Key;
import main.GameSceneManager;
import scenes.edf.gui.EDFMoneyCaption;
import scenes.edf.gui.EDFWeaponCaption;
import classes.character.shooting.ShootingCharacter;
import classes.scene.ShootingScene;

import common.CommonMethod.BackGroundColor;

public class EDFScene extends ShootingScene {
	private int money = 0;
	private EDFMainCharacterController mainCharacters;
	private EDFStageController stages;

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
		return super.updateProcess();
	}

	public boolean isPausing() {
		return stages.isPausing();
	}

	public boolean isGameOver() {
		return !(mainCharacters.earthArrive());
	}
	
	public boolean isBreakTime() {
		return stages.isBreakTime();
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
