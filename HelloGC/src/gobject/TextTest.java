package gobject;

import static main.Commons.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import texture.TextTexture;

public class TextTest extends GOCharacter {

	public TextTest() {
		setX(0);
		setY(0);
		setWidth(200);
		setHeight(30);
		setTexture(new TextTexture().createTextTexture("aasd■てすとn", getWidth(),
				getHeight(), Color.black));
	}

	@Override
	public void update() {
		getTexture().dispose();
		setTexture(new TextTexture().createTextTexture("ときtoki" + frameCount,
				getWidth(), getHeight(), Color.black));
	}

	@Override
	public void render() {
		glColor4f(0, 0, 0, 1);
		draw();
	}

}