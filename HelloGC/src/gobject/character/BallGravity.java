package gobject.character;

import static main.Commons.*;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

public class BallGravity extends Ball {
	private static final double vg = 0.25;
	protected static final double accele = 0.5;
	protected static final double yHansya = 0.8;
	protected static final double xKorogari = 0.985;
	protected static final double xHansya = 0.6;
	protected static final double sikii = 1;

	public BallGravity(int x, int y, int vx, int vy, int size) {
		super(x, y, vx, vy, size);
		Random rnd = new Random();
		this.color = new Color(rnd.nextInt(256), rnd.nextInt(256),
				rnd.nextInt(256));
	}

	public void move() {
		if (keyboard.isPress(KeyEvent.VK_LEFT))
			vx -= accele;
		if (keyboard.isPress(KeyEvent.VK_RIGHT))
			vx += accele;
		if ((y + size == HEIGHT) && (vy < sikii)
				&& (keyboard.isPress(KeyEvent.VK_SPACE))) {
			vy -= new Random().nextInt(20);
		}
		x += vx;
		if (x < 0 || x + size > WIDTH) {
			vx = -vx;
			vx *= xHansya;
			pong.play();
			if (x < 0)
				x = -x;
			if (x + size > WIDTH)
				x -= x + size - (WIDTH);
		}

		vy += vg;
		y += vy;
		if (y < 0 || y + size > HEIGHT) {
			vy = -vy;
			vy *= yHansya;
			vx *= xKorogari;
			if (y < 0) {
				y = -y;
				pong.play();
			}
			if (y + size > HEIGHT) {
				y -= y + size - (HEIGHT);
				if (Math.abs(vy) > sikii)
					pong.play();
			}
		}
	}
}
