package shirakawa.game.classes.scene;

import shirakawa.game.classes.GameObjectImpl;

/**
 * ゲームシーンの基底クラス
 * 
 * @author shirakawa
 * 
 */
public abstract class GameScene extends GameObjectImpl {
	
	@Override
	protected final void renderProcess() {
		// シーンそのものは何も描写しない
		return;
	}
}
