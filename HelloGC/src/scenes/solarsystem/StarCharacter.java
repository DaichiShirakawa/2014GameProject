package scenes.solarsystem;

import static common.CommonMethod.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import classes.character.GameCharacter;
import classes.character.GameCharacterObjectImpl;
import texture.text.FontDef;
import texture.text.TextTextureMaker;

public class StarCharacter extends GameCharacterObjectImpl implements GameCharacter {
	private static final int STAR_DEFAULT_SIZE = 20;

	private StarCharacter parentStar = null;
	private double kotenAngle = 0; //parentStarから見たthisStarの絶対方位
	private double jitenAngle = 0; //thisStarの向いている絶対方位
	private double kotenSpeed = 0;
	private double jitenSpeed = 0;
	private double timeScale;

	{
		//defaults
		setX(CENTER_X);
		setY(CENTER_Y);
		setWidth(STAR_DEFAULT_SIZE);
		setHeight(STAR_DEFAULT_SIZE);
	}

	public StarCharacter(StarCharacter parentStar, String caption, Float scale, Color color,
			double koten, double jiten) {
		this.parentStar = parentStar;
		setTexture(TextTextureMaker.createText(caption, FontDef.RICTY_48));
		setScale(scale);
		setColor(color);
		this.kotenSpeed = koten;
		this.jitenSpeed = jiten;
	}

	public StarCharacter makeChild(String caption, float scale, float hankei,
			Color color, double koten, double jiten) {
		StarCharacter childStar = new StarCharacter(this, caption, scale, color, koten, jiten);
		childStar.setX(0);
		childStar.setY(hankei);
		return childStar;
	}
	
	@Override
	protected boolean updateProcess() {
		jitenAngle += ((365 * FPS) / jitenSpeed) * timeScale;
		kotenAngle += (FPS / kotenSpeed) * timeScale;
		return super.updateProcess();
	}

	public StarCharacter setTimeScale(double timeScale) {
		this.timeScale = timeScale;
		return this;
	}

	@Override
	public void renderProcess() {
		glLoadIdentity();
		setTranslate(this);
		setGlColor4f(getColor(), 1f);
		glRotatef((float) jitenAngle, 0, 0, 1);
		drawTexture(getTexture(), getWidth(), getHeight());
	}

	/**
	 * parentStarから見た
	 */
	private static void setTranslate(StarCharacter star) {
		if (star.parentStar == null) {
			glTranslatef(star.getX(), star.getY(), 0);
		} else {
			//親星を起点として方角kotenAngleを向き、(X, Y)だけ進行する
			setTranslate(star.parentStar);
			glRotatef((float) star.kotenAngle, 0, 0, 1);
			glTranslatef(star.getX(), star.getY(), 0);
			glRotatef((float) -star.kotenAngle, 0, 0, 1);
		}
	}

	public double getJAngle() {
		return jitenAngle;
	}

	public double getKAngle() {
		return kotenAngle;
	}

	@Override
	protected boolean canDisposeTexture() {
		return true;
	}

}
