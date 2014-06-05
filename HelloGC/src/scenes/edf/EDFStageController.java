package scenes.edf;

import static common.Commons.*;
import io.Key;

import java.awt.Color;

import classes.character.TextCharacter;
import classes.scene.GameSceneImpl;
import classes.scene.ShootingScene;

public class EDFStageController extends GameSceneImpl {
	private TextCharacter startCaption;
	private TextCharacter endCaption;
	private StageState stageState = StageState.READY;
	private EDFStage currentStage;

	public EDFStageController(ShootingScene edf) {
		currentStage = add(new EDFStage(edf));
		startCaption = add(new TextCharacter("STAGE 1 START"));
		endCaption = add(new TextCharacter("CLEAR!"));
		startCaption.setX(CENTER_X)
				.setY(CENTER_Y)
				.setScale(0.5f)
				.setColor(Color.blue.brighter());
		endCaption.setX(CENTER_X)
				.setY(CENTER_Y)
				.setScale(0.5f)
				.setColor(Color.blue.brighter());

	}

	@Override
	public void update() {
		super.update();
		switch (stageState) {
		case READY:
			startCaption.show();
			endCaption.hide();
			break;
		case PLAYING:
			startCaption.hide();
			endCaption.hide();
			break;
		case CLEAR:
			startCaption.hide();
			endCaption.show();
			break;
		}
	}

	@Override
	public void inputProcess() {
		switch (stageState) {
		case READY:
			if (Key.SPACE.isPressed()) {
				stageState = StageState.PLAYING;
			}
			break;
		case PLAYING:
			if(currentStage.isClear()) {
				stageState = StageState.CLEAR;
			}
			break;
		case CLEAR:
			if(Key.SPACE.isPressed()) {
				nextStage();
				stageState = StageState.READY;
			}
			break;
		}
	}

	private void nextStage() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public boolean pausing() {
		return (stageState != StageState.PLAYING); 
	}

	private enum StageState {
		READY,
		PLAYING,
		CLEAR,
	}
}
