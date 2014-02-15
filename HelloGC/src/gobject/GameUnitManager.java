package gobject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class GameUnitManager implements GameObject {
	private List<List<GameObject>> controlLists_ = new ArrayList<>();

	@Override
	public abstract void dispose();

	@Override
	public void update() {
		for (List<GameObject> list : controlLists_) {
			for (Iterator<GameObject> ite = list.iterator(); ite.hasNext();) {
				GameObject obj = ite.next();
				obj.update();
				if (obj.canDispose()) {
					obj.dispose();
					ite.remove();
				}
			}
		}
	}

	@Override
	public abstract void render();

	@Override
	public boolean canDispose() {
		return false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected ArrayList addControlList(ArrayList list) {
		controlLists_.add(list);
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void disposeControlList(List list) {
		for (GameObject obj : (List<GameObject>) list) {
			obj.dispose();
		}
		controlLists_.remove(list);
	}

	protected List<List<GameObject>> getControlLists() {
		return controlLists_;
	}

}
