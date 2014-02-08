package io;

import gobject.GLogicObject;

import java.util.HashMap;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;

/**
 * keyStatus n==-2:離された瞬間 n==-1:おされてない n>=0:押されてからnフレームたった
 */
public class MyKeyboard extends GLogicObject {
	private static final int KEYSTATE_MOMENT_RELEASED = -2;
	private static final int KEYSTATE_NOT_PRESSED = -1;
	private static final int KEYSTATE_MOMENT_PRESSED = 0;
	private static MyKeyboard instance = new MyKeyboard();
	private HashMap<Integer, Integer> keyStatus = new HashMap<>();
	private HashMap<Integer, Boolean> nowStatus = new HashMap<>();

	protected MyKeyboard() {
		return;
	}

	public static MyKeyboard getInstance() {
		return instance;
	}

	public boolean isPress(int key) {
		return (nowStatus.get(key) != null && keyStatus.get(key) >= 0);
	}

	public boolean isPressed(int key) {
		return (keyStatus.get(key) != null && keyStatus.get(key) == KEYSTATE_MOMENT_PRESSED);
	}

	public boolean isReleased(int key) {
		return (keyStatus.get(key) != null && keyStatus.get(key) == KEYSTATE_MOMENT_RELEASED);
	}

	@Override
	public void terminate() {
		return;
	}

	@Override
	public void update() {
		// フラグリセット
		Iterator<Integer> it = nowStatus.keySet().iterator();
		while (it.hasNext()) {
			int key = it.next();
			nowStatus.put(key, false);
		}

		// いま押されてるキーにのみフラグを立てる
		while (Keyboard.next()) {
			int key = Keyboard.getEventKey();
			nowStatus.put(key, true);
		}

		// フラグ立ってるキーの状態値を更新
		it = nowStatus.keySet().iterator();
		while (it.hasNext()) {
			int key = it.next();
			if (nowStatus.get(key)) {
				if(keyStatus.get(key) == null) {
					keyStatus.put(key, KEYSTATE_NOT_PRESSED);
				}
				keyStatus.put(key, keyStatus.get(key) + 1);
			} else {
				if (keyStatus.get(key) >= 0) {
					keyStatus.put(key, KEYSTATE_MOMENT_RELEASED);
				} else if (keyStatus.get(key) == KEYSTATE_MOMENT_RELEASED) {
					keyStatus.put(key, KEYSTATE_NOT_PRESSED);
				}
			}
		}
	}
}
