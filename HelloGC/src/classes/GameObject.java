package classes;

import java.util.Iterator;

/**
 * ループで扱うオブジェクトの基底インターフェース
 * 
 * @author shirakawa
 * 
 */
public interface GameObject {

	void update();

	void render();
	
	boolean isVisible();

	void destroy();

	boolean isDestroyed();

	boolean canDestroy();

	<T extends GameObject> T add(T go);

	Iterator<GameObject> getIterator();
	
	GameObject show();
	
	GameObject hide();
	
	GameObject toggleVisible();
}
