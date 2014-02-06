package io;
import gobject.GameObject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;


public class Keyboard implements KeyListener, GameObject{
	protected HashMap<Integer, Integer> keyStatus;
	
	public Keyboard() {
		super();
		keyStatus = new HashMap<>();
	}
	
	public boolean isPress(int key) {
		return (keyStatus.get(key) != null);
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	public void keyPressed(KeyEvent e) {
		keyStatus.put(e.getKeyCode(), e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		keyStatus.remove(e.getKeyCode());
	}
}
