package classes.scene;

import classes.GameObjectImpl;

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
