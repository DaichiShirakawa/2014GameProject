package gobject;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class GameScene implements GameObject {
    private Set<GameObject> gameObjectSet = new LinkedHashSet<GameObject>();

    @Override
    public void update() {
        for (GameObject go : gameObjectSet) {
            go.update();
            if (go.canDispose()) {
                go.dispose();
                gameObjectSet.remove(go);
            }
        }
    }

    @Override
    public void render() {
        for (GameObject go : gameObjectSet) {
            go.render();
        }
    }

    @Override
    public void dispose() {
        for (GameObject go : gameObjectSet) {
            go.dispose();
        }
    }

    @Override
    public boolean canDispose() {
        return false;
    }

    protected GameObject addGO(GameObject go) {
        gameObjectSet.add(go);
        return go;
    }

    protected Iterator<GameObject> getGOIterator() {
        return gameObjectSet.iterator();
    }
}
