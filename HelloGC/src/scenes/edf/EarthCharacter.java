package scenes.edf;

import static common.Commons.*;

import java.awt.Color;

import classes.character.ShootingCharacter;
import classes.scene.ShootingScene;
import texture.text.FontDef;
import texture.text.TextTextureMaker;

public class EarthCharacter extends ShootingCharacter {
	private static final int SIZE = 45;
	private static final float JITEN = 0.1f;
	private static final Color COLOR = Color.green;

	public EarthCharacter(ShootingScene scene) {
		super(scene);
		setTexture(TextTextureMaker.createText("○", FontDef.RICTY_64));
		setX(CENTER_X);
		setY(CENTER_Y);
		setWidth(SIZE);
		setHeight(SIZE);
		setVAngle(JITEN);
		setColor(COLOR);
	}
}
