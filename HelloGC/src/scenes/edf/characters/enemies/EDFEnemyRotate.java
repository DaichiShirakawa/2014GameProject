package scenes.edf.characters.enemies;

import static common.Commons.*;

import java.awt.Color;

import texture.Texture;
import texture.TextureLoader;
import classes.scene.ShootingScene;

import common.LR;

public class EDFEnemyRotate extends EDFEnemyBase {
	private static final int POWER = 1;
	private static final int HP = 1;
	private static final int SIZE = 25;
	private static final Texture TEXTURE = TextureLoader.loadTexture(NAOKO_FOLDER_STRING
			+ "ika.png");
	private static final float ROTATE_SPEED = 0.2f;
	private static final float FALL_SPEED = 0.2f;
	private static final int MONEY = 10;
	private static final EDFEnemyProperty property = new EDFEnemyProperty(
			POWER, HP, SIZE, TEXTURE, ROTATE_SPEED, FALL_SPEED, MONEY);

	public EDFEnemyRotate(ShootingScene parentScene, float bornAngle, LR lr) {
		super(parentScene, bornAngle, lr, property);
	}

	@Override
	protected Color createColor() {
		return null;
	}

}
