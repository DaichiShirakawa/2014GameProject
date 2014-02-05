package gobject.background;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Haikei {
	private Image image;
	private int width;
	private int height;

	public Haikei(int width, int height, String filepath) {
		image = new ImageIcon(getClass().getResource(filepath)).getImage();
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, width, height, /*mainPanel*/null);
	}
}
