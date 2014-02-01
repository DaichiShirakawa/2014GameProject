package gobject;
import static main.Commons.*;
import java.awt.Color;
import java.awt.event.KeyEvent;


public class BallKeyMove extends Ball{
	protected static final double accele = 0.1;

	public BallKeyMove(int x, int y, int vx, int vy, int size) {
		super(x, y, vx, vy, size);
		this.color = Color.red;
	}
	
	public void move() {
		if (keyboard.isPress(KeyEvent.VK_UP))
			vy -= accele;
		if (keyboard.isPress(KeyEvent.VK_DOWN))
			vy += accele;
		if (keyboard.isPress(KeyEvent.VK_LEFT))
			vx -= accele;
		if (keyboard.isPress(KeyEvent.VK_RIGHT))
			vx += accele;
		super.move();
	}
}
