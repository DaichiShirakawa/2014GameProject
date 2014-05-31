package gobject.character.shooting.edf;

import static common.Commons.*;
import gobject.character.shooting.ShootingCharacter;
import gobject.scene.shooting.ShootingScene;

import java.awt.Color;

import texture.text.FontDef;
import texture.text.TextTextureMaker;

public class Earth extends ShootingCharacter {
	private static final int SIZE = 80;
	private static final float JITEN = 0.1f;
	private static final Color COLOR = Color.green;

	public Earth(ShootingScene scene) {
		super(scene);
		setTexture(TextTextureMaker.createText("☆", FontDef.RICTY_64));
		setX(CENTER_X);
		setY(CENTER_Y);
		setWidth(SIZE);
		setHeight(SIZE);
		setVAngle(JITEN);
		setColor(COLOR);
	}
}
