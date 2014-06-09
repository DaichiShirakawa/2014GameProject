package scenes.edf.stage;

import scenes.edf.EDFScene;
import scenes.edf.characters.enemies.EDFEnemyStandard;
import scenes.edf.characters.enemies.EDFEnemyToki;
import common.LR;

public class EDFStage1 extends EDFStageBase {

	public EDFStage1(EDFScene parentScene) {
		super(parentScene);
	}

	@Override
	protected void createSpawns() {
		int frame = 0;
		float angle = 0;
		addSpawn(EDFEnemyToki.class, frame, angle, LR.LEFT);
		addSpawn(EDFEnemyStandard.class, frame, angle, LR.LEFT);
		frame += 60;
		angle += 45;
		addSpawn(EDFEnemyStandard.class, frame, angle, LR.LEFT);
		frame += 60;
		angle += 45;
		addSpawn(EDFEnemyStandard.class, frame, angle, LR.LEFT);
		frame += 60;
		angle += 45;
		addSpawn(EDFEnemyStandard.class, frame, angle, LR.LEFT);
		frame += 60;
		angle += 45;
		addSpawn(EDFEnemyStandard.class, frame, angle, LR.LEFT);
		frame += 60;
		angle += 45;
		addSpawn(EDFEnemyStandard.class, frame, angle, LR.LEFT);
		frame += 60;
		angle += 45;
		addSpawn(EDFEnemyStandard.class, frame, angle, LR.LEFT);
		frame += 60;
		angle += 45;
		addSpawn(EDFEnemyStandard.class, frame, angle, LR.LEFT);
	}

}
