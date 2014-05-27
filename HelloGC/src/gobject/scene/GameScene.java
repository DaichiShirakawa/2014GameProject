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
	public GameObject add(GameObject go);

	public Iterator<GameObject> iterator();
}
