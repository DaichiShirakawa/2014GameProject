package scenes.tokishooting.stage;

import common.LR;
import scenes.tokishooting.TSScene;

class Stage6 extends StageBase {

	public Stage6(TSScene parentScene) {
		super(parentScene);
	}

	@Override
	protected void createSpawns() {
		bossSpawn(0, 0, LR.LEFT);
	}
	
	

}
