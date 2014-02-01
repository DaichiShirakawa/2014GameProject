package gobject;

import static main.Commons.*;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Rainy {
	private ArrayList<Rain> rain = new ArrayList<>();
	private int frame = 0;
	private int interval = 2;
	private int nabiki = -1;
	private int length = 8;
	private int speed = 3;

	public void update() {
		if(frame++ % interval == 0) {
			rain.add(new Rain(new Point(rnd.nextInt(WIDTH) + 50, 0)));
		}
		for(Iterator<Rain> it = rain.iterator(); it.hasNext();) it.next().update(speed, nabiki);
	}
	
	public void draw(Graphics g) {
		for(Iterator<Rain> it = rain.iterator(); it.hasNext();) {
			Rain rain= it.next();
			if(rain.isEnd(length)){
				it.remove();
				continue;
			}
			rain.draw(g, speed, nabiki, length);
		}
	}
}
