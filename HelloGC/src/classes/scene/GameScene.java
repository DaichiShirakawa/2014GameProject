package classes.scene;

import java.util.Iterator;

import classes.GameObject;

/**
 * 各画面の挙動を定義
 * 
 * @author shirakawa
 * 
 */
public interface GameScene extends GameObject {
	<T extends GameObject> T add(T go);

	Iterator<GameObject> getIterator();
}
