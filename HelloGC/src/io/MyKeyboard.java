package io;

import gobject.GLogicObject;

import java.util.HashMap;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;

/**
 * keyStatus n==-2:離された瞬間 n==-1:おされてない n>=0:押されてからnフレームたった
 */
public class MyKeyboard extends GLogicObject {
	private static final int KEYSTATE_MOMENT_RELEASED = -3;
	private static final int KEYSTATE_NOT_PRESSED = -2;
	private static final int KEYSTATE_MOMENT_PRESSED = 0;
	private static MyKeyboard instance = new MyKeyboard();
	private HashMap<Integer, Integer> keyStatus = new HashMap<>();

	protected MyKeyboard() {
		return;
	}

	public static MyKeyboard getInstance() {
		return instance;
	}

	public int getPressLength(int key) {
		if (keyStatus.get(key) == null) {
			return -1;
		} else {
			return (keyStatus.get(key));
		}
	}

	public boolean isPress(int key) {
		return (keyStatus.get(key) != null && keyStatus.get(key) >= 0);
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
		while (Keyboard.next()) {
			int key = Keyboard.getEventKey();
			if (Keyboard.getEventKeyState()) {
				// 押したとき
				// ループで+1されるので-1しておく
				keyStatus.put(key, KEYSTATE_MOMENT_PRESSED - 1);
			} else {
				// 離したとき
				keyStatus.put(key, KEYSTATE_MOMENT_RELEASED);
			}
		}
		// 押した時間更新
		Iterator<Integer> it = keyStatus.keySet().iterator();
		while (it.hasNext()) {
			int key = it.next();
			if (keyStatus.get(key) != KEYSTATE_NOT_PRESSED) {
				keyStatus.put(key, keyStatus.get(key) + 1);
			}
		}
	}

	@Override
	public void render() {
		return;
	}
}
