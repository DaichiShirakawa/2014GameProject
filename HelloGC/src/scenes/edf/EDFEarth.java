package scenes.edf;

import static common.Commons.*;

import java.awt.Color;

import texture.text.FontDef;
import texture.text.TextTextureMaker;
import classes.character.shooting.ShootingCharacter;
import classes.scene.ShootingScene;

public class EDFEarth extends ShootingCharacter{
	private static final int SIZE = 45;
	private static final float JITEN = 0.1f;
	private static final Color COLOR = Color.green;

	public EDFEarth(ShootingScene scene) {
		super(scene, Float.MAX_VALUE);
		setTexture(TextTextureMaker.createText("○", FontDef.RICTY_64));
		setTeam(TEAM.FRIEND_TEAM);
		setX(CENTER_X);
		setY(CENTER_Y);
		setWidth(SIZE);
		setHeight(SIZE);
		setVAngle(JITEN);
		setColor(COLOR);
	}

}
