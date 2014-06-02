package scenes.testball;
import io.Key;

import java.awt.Color;


public class BallKeyMove extends Ball{
	protected static final double accele = 0.1;

	public BallKeyMove(int x, int y, int vx, int vy, int size) {
		super(x, y, vx, vy, size);
		this.color = Color.red;
	}
	
	public void move() {
		if (Key.UP.isPressing())
			vy -= accele;
		if (Key.DOWN.isPressing())
			vy += accele;
		if (Key.LEFT.isPressing())
			vx -= accele;
		if (Key.RIGHT.isPressing())
			vx += accele;
		super.move();
	}
}
