package scenes.edf.stage;

import static common.Commons.*;
import io.Key;

import java.awt.Color;

import main.GameSceneManager;
import scenes.edf.EDFScene;
import classes.character.TextCharacter;
import classes.scene.GameScene;

public class EDFStageController extends GameScene {
	private EDFScene scene;
	private TextCharacter startText;
	private TextCharacter clearText;
	private StageState stageState = StageState.READY;
	private EDFStageBase currentStage;

	public EDFStageController(EDFScene scene) {
		this.scene = scene;
		StageDef.reset();
		currentStage = add(StageDef.getNextStage(scene));
		startText = add(new TextCharacter(getReadyText()));
		clearText = add(new TextCharacter("STAGE CLEAR\n" + "press enter"));
		startText.setX(CENTER_X)
				.setY(CENTER_Y + 70)
				.setScale(0.5f)
				.setColor(Color.orange.brighter());
		clearText.setX(CENTER_X)
				.setY(CENTER_Y + 70)
				.setScale(0.5f)
				.setColor(Color.orange.brighter());
	}

	private String getReadyText() {
		return StageDef.getStageName() + " READY?\n" + "press enter";
	}

	@Override
	public boolean updateProcess() {
		switch (stageState) {
		case READY:
			startText.show();
			clearText.hide();
			break;
		case PLAYING:
			startText.hide();
			clearText.hide();
			if (currentStage.isClear()) {
				nextStage();
				if (currentStage == null) {
					stageState = StageState.ALL_CLEAR;
				} else {
					stageState = StageState.CLEAR;
				}
			}
			break;
		case CLEAR:
		case ALL_CLEAR:
			startText.hide();
			clearText.show();
			break;
		}
		return !scene.isPausing();
	}

	@Override
	protected boolean inputProcess() {
		switch (stageState) {
		case READY:
			if (Key.RETURN.isPressed()) {
				stageState = StageState.PLAYING;
			}
			break;
		case CLEAR:
			if (Key.RETURN.isPressed()) {
				stageState = StageState.READY;
			}
			break;
		case ALL_CLEAR:
			if (Key.RETURN.isPressed()) {
				GameSceneManager.getInstance()
						.gameReset();
			}
			break;
		default:
			break;
		}
		return !scene.isBreakTime();
	}

	private void nextStage() {
		currentStage.destroy();
		currentStage = add(StageDef.getNextStage(scene));
		if (StageDef.allClear()) {
			clearText.updateText("GAME CLEAR!\n" + "congratulations!");
			return;
		}
		startText.updateText(getReadyText());
	}

	private enum StageState {
		READY,
		PLAYING,
		CLEAR,
		ALL_CLEAR,
	}

	public boolean isBreakTime() {
		return (stageState == StageState.READY)
				|| (stageState == StageState.CLEAR);
	}

	public boolean isPlaying() {
		return (stageState == StageState.PLAYING);
	}

}
