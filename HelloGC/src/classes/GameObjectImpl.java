package classes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * ひとつのオブジェクトは、自分を含めた複数の子オブジェクトを所有できる。
 * updateやrenderは自身およびすべての子に対して行われる。
 * 自分がdestroyされた時、すべての子オブジェクトもdestroyされる。
 * 
 * @author shirakawa
 * 
 */
public abstract class GameObjectImpl implements GameObject {
	private List<GameObject> children = new LinkedList<>();
	// childrenのイテレート中にaddさせないため、addしたオブジェクトを一時保管する
	private List<GameObject> bookingObjects = new LinkedList<>();

	private boolean destroyed = false;
	private boolean visible = true;

	@Override
	public final void update() {
		addBookingObjects();
		if (isDestroyed()) {
			return;
		}
		if (!updateProcess()) {
			return;
		}
		inputProcess();
		updateChildren();
	}

	/**
	 * 一時保管したオブジェクトを正式に子として迎える
	 */
	protected final void addBookingObjects() {
		children.addAll(bookingObjects);
		bookingObjects.clear();
	}

	/**
	 * update中に呼ばれる入力処理。
	 * 必要に応じてオーバーライドする。
	 */
	protected void inputProcess() {
	}

	/**
	 * 自身のメイン処理を記述する。
	 * falseを返せば入力処理や子のアップデートは行われない。
	 * ポーズ処理など、一時的に止めたい場合にfalseを返す。
	 */
	abstract protected boolean updateProcess();

	/**
	 * 子オブジェクトのメイン処理を呼ぶ
	 */
	protected final void updateChildren() {
		for (Iterator<GameObject> ite = getIterator(); ite.hasNext();) {
			GameObject go = ite.next();

			if (go.isDestroyed()) {
				ite.remove();
				continue;
			}

			((GameObjectImpl) go).update();
		}
	}

	@Override
	public final void render() {
		addBookingObjects();
		if (!isVisible()) {
			return;
		}
		renderProcess();
		renderchildren();
	}

	/**
	 * 自身のメイン処理を記述する
	 */
	protected abstract void renderProcess();

	/**
	 * 子オブジェクトの描写処理
	 */
	private final void renderchildren() {
		for (GameObject go : children) {
			((GameObjectImpl) go).render();
		}
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public final void destroy() {
		addBookingObjects();
		destroyProcess();
		destroyChildren();
	}

	/**
	 * 自身の破棄処理を記述する。
	 * テクスチャ破棄の追加が必要ならオーバーライドする。
	 */
	protected void destroyProcess() {
		destroyed = true;
	}

	/**
	 * 子オブジェクトの破棄処理を呼ぶ
	 */
	private void destroyChildren() {
		for (GameObject go : children) {
			((GameObjectImpl) go).destroyProcess();
		}
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
		return children.iterator();
	}

	@Override
	public final List<GameObject> getChildrenCopy() {
		return new LinkedList<>(children);
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
