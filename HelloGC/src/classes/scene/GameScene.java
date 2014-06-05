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
	public final boolean canDestroy() {
		return false;
	}
}
