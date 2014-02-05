package gobject.galaxy;

import static main.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.GOCharacter;

import java.awt.Color;
import java.util.ArrayList;

import texture.TextTexture;
import texture.Texture;

public class SolarSystem extends GOCharacter {
	private ArrayList<Star> stars;
	public static final float TIME_SCALE = 0.0005f;

	private Texture fontTexture;
	private float keika = 0;

	public SolarSystem() {
		Star star;
		stars = new ArrayList<>();
		Star sun = new Star(null, "太", 1f, Color.orange, 0, 27.275f);
		stars.add(sun);
		stars.add(sun.makeChild("水", 0.4f, 30, new Color(0.5f, 0.5f, 1f),
				0.241f, 58.65f));
		stars.add(sun.makeChild("金", 0.5f, 60, Color.yellow, 0.615f, 243.0187f));

		star = sun.makeChild("地", 0.6f, 90, new Color(0.5f, 1f, 0.5f), 1f,
				0.997271f);
		stars.add(star);
		stars.add(star
				.makeChild("月", 0.3f, 15, Color.white, 0.07589f, 27.3127f));

		star = sun.makeChild("火", 0.5f, 120, Color.red, 1.881f, 1.02595f);
		stars.add(star);
		stars.add(star.makeChild("月", 0.3f, 15, Color.white, 0.07589f, 0.0748f));

		star = sun.makeChild("木", 0.8f, 150, new Color(0.9f, 0.7f, 0.5f),
				11.86f, 0.4135f);
		stars.add(star);
		stars.add(star.makeChild("フ", 0.3f, 15, Color.red, 0.3f, 0.5f));

		stars.add(sun.makeChild("土", 0.7f, 180, new Color(0.9f, 0.7f, 0.5f),
				29.46f, 0.4264f));

		stars.add(sun.makeChild("天", 0.4f, 210, Color.white, 84.01f, 0.7181f));
		fontTexture = new TextTexture().createTextTexture(keika + "日経過",
				FONT_HEIGHT, FONT_HEIGHT, Color.white);
	}

	@Override
	public void update() {
		for (Star star : stars) {
			star.update();
		}
		keika += 365 * FPS / 360 * TIME_SCALE;
		fontTexture.dispose();
		fontTexture = new TextTexture().createTextTexture((int)keika + "日経過", 300,
				FONT_HEIGHT, Color.white);

	}

	@Override
	public void render() {
		for (Star star : stars) {
			star.render();
		}

		glLoadIdentity();
		glTranslatef(60, HEIGHT - 10, 0);
		drawTexture(fontTexture, 100, 10);
	}

}
