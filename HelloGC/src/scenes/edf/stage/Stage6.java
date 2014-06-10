package scenes.edf.stage;

import common.LR;

import scenes.edf.EDFScene;

class Stage6 extends StageBase {

	public Stage6(EDFScene parentScene) {
		super(parentScene);
	}

	@Override
	protected void createSpawns() {
		bossSpawn(0, 0, LR.LEFT);
	}
	
	

}
