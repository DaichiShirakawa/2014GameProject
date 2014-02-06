package gobject.galaxy;

import static main.Commons.*;
import static gobject.galaxy.SolarSystem.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import texture.TextTexture;
import gobject.GOCharacter;

public class Star extends GOCharacter {
	private Star parentStar;
	private String caption;
	private float kAngle = 0;
	private float jAngle = 0;
	private float koten = 0.1f;
	private float jiten = 0.1f;
	private Color color;

	public Star(Star parentStar, String caption, Float scale, Color color,
			float koten, float jiten) {
		this.parentStar = parentStar;
		this.caption = caption;
		this.color = color;
		setScale(scale);
		setX(WIDTH / 2);
		setY(HEIGHT / 2);
		setTexture(new TextTexture().createTextTexture(this.caption,
				FONT_HEIGHT, FONT_HEIGHT, this.color));
		setWidth(24);
		setHeight(24);
		setAngle(0);
		kAngle = random(0, 360);
		jAngle = random(0, 360);
		this.koten = koten;
		this.jiten = jiten;
	}

	public Star makeChild(String caption, float scale, float hankei,
			Color color, float koten, float jiten) {
		Star childStar = new Star(this, caption, scale, Color.white, koten,
				jiten);
		childStar.setX(0);
		childStar.setY(hankei);
		return childStar;
	}

	@Override
	public void update() {
		jAngle += 365 * FPS / jiten * timeScale;
		kAngle += FPS / koten * timeScale;
		// setAngle(getAngle() - koten / FPS * 360);
	}

	@Override
	public void render() {
		// 設定を初期化する
		glLoadIdentity();
		setTranslate(this);
		glRotatef(jAngle, 0, 0, 1);
		drawTexture(getTexture(), getWidth(), getHeight());
	}

	private void setTranslate(Star star) {
		if (star.parentStar == null) {
			glTranslatef(star.getX(), star.getY(), 0);
			// glRotatef(star.koten, 0, 0, 1);
		} else {
			setTranslate(star.parentStar);
			glRotatef(star.kAngle, 0, 0, 1);
			glTranslatef(star.getX(), star.getY(), 0);
			glRotatef(-star.kAngle, 0, 0, 1);
		}
	}

}
