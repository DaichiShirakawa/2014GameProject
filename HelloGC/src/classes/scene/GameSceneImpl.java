package classes.scene;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import classes.GameObject;

/**
 * シーンの挙動を実装
 * 
 * @author shirakawa
 * 
 */
public abstract class GameSceneImpl implements GameScene {
	private Collection<GameObject> gameObjects = new LinkedList<>();
	private Collection<GameObject> bookingObjects = new LinkedList<>();

	@Override
	public void update() {
		addBookingObjects();
		inputProcess();
		for (GameObject go : gameObjects) {
			go.update();
			if (go.canDispose()) {
				go.dispose();
				gameObjects.remove(go);
			}
		}
	}

	@Override
	public void inputProcess() {
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
		addBookingObjects();
		for (GameObject go : gameObjects) {
			go.dispose();
		}
	}

	@Override
	public boolean canDispose() {
		return false;
	}

	@Override
	public <T extends GameObject> T add(T go) {
		bookingObjects.add(go);
		return go;
	}

	protected void addBookingObjects() {
		gameObjects.addAll(bookingObjects);
		bookingObjects.clear();
	}

	@Override
	public Iterator<GameObject> getIterator() {
		return gameObjects.iterator();
	}

}
