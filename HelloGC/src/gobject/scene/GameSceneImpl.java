package gobject.scene;

import gobject.GameObject;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * シーンの挙動を実装
 * 
 * @author shirakawa
 *
 */
public abstract class GameSceneImpl implements GameScene {
    private Collection<GameObject> gameObjects = new LinkedList<GameObject>();
    
	@Override
    public void update() {
        for (GameObject go : gameObjects) {
            go.update();
            if (go.canDispose()) {
                go.dispose();
                gameObjects.remove(go);
            }
        }
    }

    @Override
    public void render() {
        for (GameObject go : gameObjects) {
            go.render();
        }
    }

    @Override
    public void dispose() {
        for (GameObject go : gameObjects) {
            go.dispose();
        }
    }

    @Override
    public boolean canDispose() {
        return false;
    }

    public GameObject add(GameObject go) {
        gameObjects.add(go);
        return go;
    }

    public Iterator<GameObject> iterator() {
        return gameObjects.iterator();
    }
    
}
