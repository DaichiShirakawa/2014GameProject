package gobject.scene;

import gobject.GameObject;

import java.util.Iterator;

/**
 * 各画面の挙動を定義
 * 
 * @author shirakawa
 *
 */
public interface GameScene extends GameObject {	
	GameObject add(GameObject go);

	Iterator<GameObject> iterator();
}
