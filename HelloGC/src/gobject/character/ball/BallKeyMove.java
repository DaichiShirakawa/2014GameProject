package gobject.character.ball;
import static common.Commons.*;

import java.awt.Color;

import common.Commons.KEY;


public class BallKeyMove extends Ball{
	protected static final double accele = 0.1;

	public BallKeyMove(int x, int y, int vx, int vy, int size) {
		super(x, y, vx, vy, size);
		this.color = Color.red;
	}
	
	public void move() {
		if (KEYBOARD.isPressing(KEY.UP))
			vy -= accele;
		if (KEYBOARD.isPressing(KEY.DOWN))
			vy += accele;
		if (KEYBOARD.isPressing(KEY.LEFT))
			vx -= accele;
		if (KEYBOARD.isPressing(KEY.RIGHT))
			vx += accele;
		super.move();
	}
}
