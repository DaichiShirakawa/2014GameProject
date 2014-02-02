package gobject.character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class BallLauncher {
	private Point p1, p2;
	
	public BallLauncher(Point point) {
		p1 = point;
		p2 = point;
	}
	
	public void update(Point point) {
		p2 = point;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
	}

	public Ball launch(Point point, int size) {
		p2 = point;
		return new BallGravity(p1.x - size / 2, p1.y - size / 2,
				 (int)(p1.getX() - p2.getX()) / 3, (int)(p1.getY() - p2.getY()) / 3, size);
	}

}
