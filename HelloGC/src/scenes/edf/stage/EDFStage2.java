package scenes.edf.stage;

import scenes.edf.EDFScene;
import scenes.edf.characters.enemies.EDFEnemyRotate;
import common.LR;

public class EDFStage2 extends EDFStageBase {

	public EDFStage2(EDFScene parentScene) {
		super(parentScene);
	}

	@Override
	protected void createSpawns() {
		int frame = 0;
		float angle = 0;
		frame += 60;
		angle += 90;
		addSpawn(EDFEnemyRotate.class, frame, angle, LR.LEFT);
		addSpawn(EDFEnemyRotate.class, frame, angle, LR.RIGHT);
		frame += 120;
		addSpawn(EDFEnemyRotate.class, frame, angle, LR.LEFT);
		addSpawn(EDFEnemyRotate.class, frame, angle, LR.RIGHT);
		frame += 120;
		angle += 180;
		addSpawn(EDFEnemyRotate.class, frame, angle, LR.LEFT);
		addSpawn(EDFEnemyRotate.class, frame, angle, LR.RIGHT);
		frame += 120;
		addSpawn(EDFEnemyRotate.class, frame, angle, LR.LEFT);
		addSpawn(EDFEnemyRotate.class, frame, angle, LR.RIGHT);
	}

}
