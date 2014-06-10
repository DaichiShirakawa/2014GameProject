package shirakawa.game.scenes.tokishooting.stage;

import shirakawa.game.common.LR;
import shirakawa.game.scenes.tokishooting.TokiShootingScene;

class Stage3 extends StageBase {

	public Stage3(TokiShootingScene parentScene) {
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
