package classes;

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

	void inputProcess();
}
