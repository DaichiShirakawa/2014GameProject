package gobject.weather;

import static main.Commons.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

public class Rainy {
	private ArrayList<Rain> rain = new ArrayList<>();
	private int frame = 0;
	private int interval = 2;
	private int nabiki = -1;
	private int length = 8;
	private int speed = 3;

	public void update() {
		if (frame++ % interval == 0) {
			rain.add(new Rain(new Point(rnd.nextInt(WIDTH + 100), -length)));
		}
		for (Iterator<Rain> it = rain.iterator(); it.hasNext();)
			it.next().update(speed, nabiki);
	}

	public void draw(Graphics g) {
		for (Iterator<Rain> it = rain.iterator(); it.hasNext();) {
			Rain rain = it.next();
			if (rain.isEnd(length)) {
				it.remove();
				continue;
			}
			rain.draw(g, speed, nabiki, length);
		}
	}

	public class Rain {
		private Point point;
		private Color color;

		public Rain(Point point) {
			this.point = point;
			int cl = 100 + rnd.nextInt(150);
			color = new Color(cl, cl, 255);
		}

		public boolean isEnd(int length) {
			return point.getY() > HEIGHT + length;
		}

		public void update(int speed, int nabiki) {
			point.move(point.x + nabiki, point.y + speed);
		}

		public void draw(Graphics g, int speed, int nabiki, int length) {
			g.setColor(color);
			g.drawLine(point.x, point.y, point.x + nabiki, point.y + length);
		}
	}

}
