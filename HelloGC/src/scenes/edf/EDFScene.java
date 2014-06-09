package scenes.edf;

import io.Key;

import java.util.Iterator;

import main.GameSceneManager;
import scenes.edf.characters.EDFCharacterController;
import scenes.edf.gui.moneycaption.EDFMoneyCaption;
import scenes.edf.gui.weaponcaption.EDFWeaponCaption;
import scenes.edf.stage.EDFStageController;
import classes.GameObject;
import classes.character.shooting.ShootingCharacter;
import classes.character.shooting.ShootingCharacterImpl.ShootingTeam;
import classes.scene.ShootingScene;
import common.CommonMethod.BackGroundColor;

public class EDFScene extends ShootingScene {
	private int money = 0;
	private EDFCharacterController characters;
	private EDFStageController stages;
	private boolean pausing = false;

	public EDFScene() {
		BackGroundColor.BLACK.set();
		characters = add(new EDFCharacterController(this));
		stages = add(new EDFStageController(this));
		add(new EDFMoneyCaption(this));
		add(new EDFWeaponCaption(characters));
	}

	/**
	 * 自機、敵機、弾丸などはSceneにaddせず、下請けクラスに処理を移譲する。
	 */
	@Override
	public <T extends GameObject> T add(T go) {
		if (go instanceof ShootingCharacter) {
			return characters.add(go);
		} else {
			return super.add(go);
		}
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
		return !(characters.earthArrive());
	}

	@Override
	public boolean inputProcess() {
		// TODO テスト用強制ゲームオーバー。
		if (Key.O.isPressed()) {
			GameSceneManager.getInstance()
					.gameover();
		}

		// TODO テスト用強制クリア。
		if (Key.A.isPressed()) {
			stages.doClear();
		}

		// TODO ポーズ中キャプションがほしい
		if (Key.P.isPressed()) {
			if (isPlaying()) {
				pausing = !pausing;
			}
		}

		return super.inputProcess();
	}

	/**
	 * 生存している敵属性のキャラや弾を一掃する
	 */
	public void clearEnemies() {
		for (Iterator<GameObject> ite = characters.getIterator(); ite.hasNext();) {
			ShootingCharacter character = (ShootingCharacter) ite.next();
			if (character.getTeam() == ShootingTeam.ENEMY_TEAM) {
				character.damage(Integer.MAX_VALUE);
				ite.remove();
			}
		}
	}
}
