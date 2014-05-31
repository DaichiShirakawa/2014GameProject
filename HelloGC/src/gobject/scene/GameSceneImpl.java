package gobject.scene;

import gobject.GameObject;
import gobject.character.GameCharacter;

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
		processInput();
		for (GameObject go : gameObjects) {
			go.update();
			if (go.canDispose()) {
				go.dispose();
				gameObjects.remove(go);
			}
		}
	}

	protected void processInput() {
		// 必要に応じてオーバーライド
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

//	@Override
//	public GameObject add(GameObject go) {
//		gameObjects.add(go);
//		return go;
//	}

	@Override
	public <T extends GameObject> T add(T go) {
		gameObjects.add(go);
		return go;
	}

	@Override
	public Iterator<GameObject> getIterator() {
		return gameObjects.iterator();
	}

}
