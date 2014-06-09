package scenes.edf.characters.enemies;

import static common.CommonMethod.*;

import java.awt.Color;

import classes.scene.ShootingScene;
import texture.Texture;
import texture.text.TextTextureMaker;
import common.LR;

public class EDFEnemyStandard extends EDFEnemyBase {
	private static final int POWER = 1;
	private static final int HP = 1;
	private static final int SIZE = 20;
	private static final Texture TEXTURE = TextTextureMaker.createText("†");
	private static final float ROTATE_SPEED = 0;
	private static final float FALL_SPEED = 0.3f;
	private static final int MONEY = 5;
	private static final EDFEnemyProperty property = new EDFEnemyProperty(
			POWER, HP, SIZE, TEXTURE, ROTATE_SPEED, FALL_SPEED, MONEY);

	public EDFEnemyStandard(ShootingScene parentScene, float bornAngle, LR lr) {
		super(parentScene, bornAngle, lr, property);
	}

	@Override
	protected Color createColor() {
		return new Color(random(0.5f, 0.7f), random(0.5f, 0.7f), random(0.7f, 1f));
	}
}
