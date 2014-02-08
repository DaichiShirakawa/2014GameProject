package gobject.galaxy;

import static common.CommonLogic.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.GCharacterObect;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import texture.TextTexture;
import texture.Texture;
import texture.TextureLoader;

public class SolarSystem extends GCharacterObect {
	private ArrayList<Star> stars;
	public static float timeScale = 0.001f;

	private Texture tokiTexture;
	private Texture fontTexture;
	private float keika = 0;
	private float tokiy, tokix;

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
		try {
			tokiTexture = new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
					+ "dotTokiIcon.png");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		for (Star star : stars) {
			star.update();
		}
		keika += 365 * FPS / 360 * timeScale;
		fontTexture.dispose();
		fontTexture = new TextTexture().createTextTexture((int) keika + "日経過",
				300, FONT_HEIGHT, Color.white);

		if (keyboard.isPress(KEY_UP)) {
			timeScale += 0.0001;
		} else if (keyboard.isPress(KEY_DOWN)) {
			timeScale -= 0.0001;
		} else if (keyboard.isPress(KEY_LEFT)) {
			tokix = WIDTH;
			tokiy = HEIGHT;
		}

		tokix -= 3;
		tokiy -= 2;
	}

	@Override
	public void render() {
		for (Star star : stars) {
			star.render();
		}

		glLoadIdentity();
		glTranslatef(60, HEIGHT - 10, 0);
		drawTexture(fontTexture, 100, 10);
		glLoadIdentity();
		glTranslatef(tokix, tokiy, 0);
		drawTexture(tokiTexture, 20, 20);
	}

	@Override
	public void terminate() {
		tokiTexture.dispose();
		fontTexture.dispose();
		super.terminate();
	}

}
