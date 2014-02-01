package gobject;

import static main.Commons.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rain {
	private Point point;
	private Color color;
	
	public Rain(Point point) {
		this.point = point;
		int cl = 100 + rnd.nextInt(100);
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
		g.drawLine(point.x, point.y, point.x + nabiki , point.y + length);
	}
	}
