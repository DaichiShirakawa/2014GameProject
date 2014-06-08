package scenes.edf.stage;

import scenes.edf.EDFScene;
import scenes.edf.enemies.EDFEnemy;
import common.LR;

public class EDFStage1 extends EDFStageBase {

	public EDFStage1(EDFScene parentScene) {
		super(parentScene);
	}

	@Override
	protected void createSpawns() {
		int frame = 60;
		float angle = 0;
		addSpawn(EDFEnemy.class, frame, angle, LR.LEFT);
		addSpawn(EDFEnemy.class, frame, angle, LR.RIGHT);
		frame += 120;
		addSpawn(EDFEnemy.class, frame, angle, LR.LEFT);
		addSpawn(EDFEnemy.class, frame, angle, LR.RIGHT);
		frame += 120;
		angle += 180;
		addSpawn(EDFEnemy.class, frame, angle, LR.LEFT);
		addSpawn(EDFEnemy.class, frame, angle, LR.RIGHT);
		frame += 120;
		addSpawn(EDFEnemy.class, frame, angle, LR.LEFT);
		addSpawn(EDFEnemy.class, frame, angle, LR.RIGHT);
	}

}
