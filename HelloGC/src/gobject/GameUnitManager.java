package gobject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class GameUnitManager implements GameObject {
    private List<List<GameObject>> controlLists = new ArrayList<List<GameObject>>();

    @Override
    public abstract void dispose();

    @Override
    public void update() {
        for (List<GameObject> list : controlLists) {
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

    protected ArrayList addControlList(ArrayList list) {
        controlLists.add(list);
        return list;
    }

    protected void disposeControlList(List list) {
        for (GameObject obj : (List<GameObject>) list) {
            obj.dispose();
        }
        controlLists.remove(list);
    }

    protected List<List<GameObject>> getControlLists() {
        return controlLists;
    }

}
