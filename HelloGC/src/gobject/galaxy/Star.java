package gobject.galaxy;

import static main.Commons.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import texture.TextTexture;
import gobject.GOCharacter;

public class Star  extends GOCharacter{
	private Star parentStar;
	private String caption;
	private float koten = 0.1f;
	public Star(Star parentStar, String caption) {
		this.parentStar = parentStar;
		this.caption = caption;
		setScale(1);
		setX(WIDTH / 2);
		setY(HEIGHT / 2);
		setTexture(new TextTexture().createTextTexture(this.caption, FONT_HEIGHT, FONT_HEIGHT, Color.white));
		setWidth(getTexture().getWidth());
		setHeight(getTexture().getHeight());
		setAngle(random(0f, 360f));
	}
	
	public Star makeChild(String caption, float hankei) {
		Star childStar = new Star(this, caption);
		childStar.setX(0);
		childStar.setY(hankei);
		return childStar;
	}

	@Override
	public void update() {
		setAngle(getAngle() - koten / FPS * 360);
	}

	@Override
	public void render() {
        //  設定を初期化する
        glLoadIdentity();
        setTranslate(this);
		drawTexture(getTexture(), getWidth(), getHeight());
	}
	
	private void setTranslate(Star star) {
		if(star.parentStar == null) {
			glTranslatef(star.getX(), star.getY(), 0);
			glRotatef(star.getAngle(), 0, 0, 1);
		}else{
			setTranslate(star.parentStar);
			glTranslatef(star.getX(), star.getY(), 0);
			glRotatef(star.getAngle(), 0, 0, 1);			
		}
	}

}
