package classes;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * ひとつのオブジェクトは、自分を含めた複数のオブジェクトを所有できる。
 * 処理や描写は所有するオブジェクト全体に対して行われる。
 * 自分がdestroyされた時、管理下のオブジェクトもすべてdestroyされる。
 * 
 * @author shirakawa
 * 
 */
public abstract class GameObjectImpl implements GameObject {
	private Collection<GameObject> gameObjects = new LinkedList<>();
	private Collection<GameObject> bookingObjects = new LinkedList<>();

	private boolean destroyed = false;
	private boolean visible = true;

	protected GameObjectImpl() {
		gameObjects.add(this);
	}

	@Override
	public final void update() {
		addBookingObjects();
		inputProcess();
		updateObjects();
	}

	protected final void addBookingObjects() {
		gameObjects.addAll(bookingObjects);
		bookingObjects.clear();
	}

	protected void inputProcess() {
		// 入力処理。必要に応じてオーバーライドする。
	}

	protected void updateObjects() {
		for (Iterator<GameObject> ite = getIterator(); ite.hasNext();) {
			GameObject go = ite.next();
			if (go.canDestroy()) {
				go.destroy();
				ite.remove();
			} else {
				((GameObjectImpl) go).updateProcess();
			}
		}
	}

	/**
	 * 各オブジェクトのメイン処理。
	 */
	abstract protected void updateProcess();

	@Override
	public final void render() {
		for (GameObject go : gameObjects) {
			if (!go.isDestroyed() && go.isVisible()) {
				((GameObjectImpl) go).renderProcess();
			}
		}
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	/**
	 * 各オブジェクトの描写処理。
	 */
	protected abstract void renderProcess();

	/**
	 * オブジェクトの明示的な破棄を行う。
	 */
	@Override
	public final void destroy() {
		destroyed = true;
		addBookingObjects();
		for (GameObject go : gameObjects) {
			((GameObjectImpl) go).destroyProcess();
		}
	}

	protected void destroyProcess() {
		// テクスチャ破棄等の処理。必要に応じてオーバーライド
	}

	@Override
	public final boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public final <T extends GameObject> T add(T go) {
		bookingObjects.add(go);
		return go;
	}

	@Override
	public final Iterator<GameObject> getIterator() {
		return gameObjects.iterator();
	}

	@Override
	public final GameObject show() {
		visible = true;
		return this;
	}

	@Override
	public final GameObject hide() {
		visible = false;
		return this;
	}

	@Override
	public final GameObject toggleVisible() {
		visible = !visible;
		return this;
	}

}
