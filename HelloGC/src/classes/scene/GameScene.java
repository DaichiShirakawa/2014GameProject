package classes.scene;

import classes.GameObjectImpl;

/**
 * シーン
 * 
 * @author shirakawa
 * 
 */
public abstract class GameScene extends GameObjectImpl{
	@Override
	protected final void renderProcess() {
		//シーンは何も描写しない
	}
}
