package gobject;

import java.util.ArrayList;

public abstract class GLogicObject implements GameObject {
	protected ArrayList<GameObject> children_ = new ArrayList<>();
	

	@Override
	public abstract void terminate();

	@Override
	public abstract void update();

	@Override
	public abstract void render();
	
	@Override
	public boolean canDispose() {
		return false;
	}
	
	protected GameObject addChild(GameObject gameObject) {
		children_.add(gameObject);
		return gameObject;
	}
	
	protected void disposeChild(GameObject gameObject) {
		gameObject.terminate();
		children_.remove(gameObject);
	}

}
