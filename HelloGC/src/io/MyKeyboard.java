package io;

import gobject.GameObject;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import common.Commons.KEY;

/**
 * キーボードの状態を扱いやすくするためのラッパー
 * 押した瞬間／離した瞬間／押されてから経過したフレーム　をキーごとに保持する。
 */
public class MyKeyboard implements GameObject {
	// HACK 整数の線形性を前提とした定数。もう少し賢い方法がありそうだ
	private static final int KEYSTATE_RELEASED = -3; // 離された瞬間
	private static final int KEYSTATE_NOTOUCH = -2; // 押されていない
	private static final int KEYSTATE_PRESSED = 0; // 押された瞬間

	private static MyKeyboard instance = null;
	
	private HashMap<KEY, Integer> keyboardStatus = new HashMap<>();

	private MyKeyboard() {
		// 隠蔽
	}

	public static MyKeyboard getInstance() {
		if (instance == null) {
			instance = new MyKeyboard();
		}
		return instance;
	}

	public int getPressingFrameCount(KEY key) {
		if (keyboardStatus.get(key) == null) {
			return KEYSTATE_NOTOUCH;
		} else {
			return (keyboardStatus.get(key));
		}
	}

	public boolean isPressing(KEY key) {
		return (keyboardStatus.get(key) != null && keyboardStatus.get(key) >= 0);
	}

	public boolean isPressed(KEY key) {
		return (keyboardStatus.get(key) != null && keyboardStatus.get(key) == KEYSTATE_PRESSED);
	}

	public boolean isReleased(KEY key) {
		return (keyboardStatus.get(key) != null && keyboardStatus.get(key) == KEYSTATE_RELEASED);
	}

	@Override
	public final void update() {
		updatePressOrRelease();
		updatePressedFrameCount();
	}

	private void updatePressedFrameCount() {
		for (KEY key : keyboardStatus.keySet()) {
			if (keyboardStatus.get(key) != KEYSTATE_NOTOUCH) {
				keyboardStatus.put(key, keyboardStatus.get(key) + 1);
			}
		}
	}

	private void updatePressOrRelease() {
		while (Keyboard.next()) {
			KEY key = KEY.valueOf(Keyboard.getEventKey());
			if (Keyboard.getEventKeyState()) {
				// 押した瞬間
				// 押下時間更新時に+1されるため、ここで-1しておく。
				keyboardStatus.put(key, KEYSTATE_PRESSED - 1);
			} else {
				// 離した瞬間
				// こちらも後で+1されるので、ここで-1しておく。
				keyboardStatus.put(key, KEYSTATE_RELEASED - 1);
			}
		}
	}

	@Override
	public void render() {
		return;
	}

	@Override
	public void dispose() {
		return;
	}

	@Override
	public boolean canDispose() {
		return false;
	}
}
