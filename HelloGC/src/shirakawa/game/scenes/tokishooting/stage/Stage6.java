package shirakawa.game.scenes.tokishooting.stage;

import shirakawa.game.common.LR;
import shirakawa.game.scenes.tokishooting.TokiShootingScene;

class Stage6 extends StageBase {

	public Stage6(TokiShootingScene parentScene) {
		super(parentScene);
	}

	@Override
	protected void createSpawns() {
		bossSpawn(0, 0, LR.LEFT);
	}
	
	

}
