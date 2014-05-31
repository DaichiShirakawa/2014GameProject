package gobject;

/**
 * ループで扱うオブジェクトの基底インターフェース
 * 
 * @author shirakawa
 * 
 */
public interface GameObject {
	
	void update();
	
	void render();

	void dispose();

	boolean canDispose();
}
