package scenes.edf.stage;

import scenes.edf.EDFScene;

import common.LR;

class Stage3 extends StageBase {

	public Stage3(EDFScene parentScene) {
		super(parentScene);
	}

	@Override
	protected void createSpawns() {
		int frame = 0;
		float angle = 0;
		LR lr = LR.RIGHT;
		
		tokiSpawn(frame += 60, angle, lr = lr.toggle());
		tokiSpawn(frame += 600, angle, lr = lr.toggle());
	}

}
