package scenes.edf;

import classes.scene.GameSceneImpl;
import classes.scene.ShootingScene;

public class EDFStageController extends GameSceneImpl {
	private ShootingScene edf;

	public EDFStageController(ShootingScene edf) {
		this.edf = edf;
		add(new EDFStage(edf));
	}

	public boolean pausing() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
