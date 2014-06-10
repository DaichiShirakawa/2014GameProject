package scenes.tokishooting.stage;

import common.LR;
import scenes.tokishooting.TokiShootingScene;

class Stage6 extends StageBase {

	public Stage6(TokiShootingScene parentScene) {
		super(parentScene);
	}

	@Override
	protected void createSpawns() {
		bossSpawn(0, 0, LR.LEFT);
	}
	
	

}
