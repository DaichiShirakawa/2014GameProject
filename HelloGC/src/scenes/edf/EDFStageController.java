package scenes.edf;

import static common.Commons.*;
import io.Key;

import java.awt.Color;

import classes.character.TextCharacter;
import classes.scene.GameScene;

public class EDFStageController extends GameScene {
	private TextCharacter startText;
	private TextCharacter clearText;
	private StageState stageState = StageState.READY;
	private EDFStage currentStage;

	public EDFStageController(EDFScene edf) {
		currentStage = add(new EDFStage(edf));
		startText = add(new TextCharacter("STAGE 1 READY?\n" + "press enter"));
		clearText = add(new TextCharacter("CLEAR!\n" + "press enter"));
		startText.setX(CENTER_X)
				.setY(CENTER_Y)
				.setScale(0.5f)
				.setColor(Color.blue.brighter());
		clearText.setX(CENTER_X)
				.setY(CENTER_Y)
				.setScale(0.5f)
				.setColor(Color.blue.brighter());
	}

	@Override
	public boolean updateProcess() {
		switch (stageState) {
		case READY:
			startText.show();
			clearText.hide();
			// HACK ポーズ中の入力処理
			if (Key.RETURN.isPressed()) {
				stageState = StageState.PLAYING;
			}
			break;
		case PLAYING:
			startText.hide();
			clearText.hide();
			if (currentStage.isClear()) {
				stageState = StageState.CLEAR;
			}
			break;
		case CLEAR:
			startText.hide();
			clearText.show();
			// HACK ポーズ中の入力処理
			if (Key.RETURN.isPressed()) {
				nextStage();
				stageState = StageState.READY;
			}
			break;
		}
		return !isPausing();
	}

	private void nextStage() {
		// TODO 自動生成されたメソッド・スタブ

	}

	public boolean isPausing() {
		return false;
		// return stageState == StageState.READY;
	}

	private enum StageState {
		READY,
		PLAYING,
		CLEAR,
	}

	public boolean isBreakTime() {
		return (stageState == StageState.READY)
				|| (stageState == StageState.CLEAR);
	}

}
