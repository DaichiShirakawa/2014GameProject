package scenes.edf.characters.enemies;

import static common.CommonMethod.*;
import static common.Commons.*;

import java.awt.Color;

import classes.scene.ShootingScene;
import main.FPSManager;
import scenes.edf.EDFScene;
import scenes.edf.weapons.enemies.EDFNormalEnemyBullet;
import texture.Texture;
import texture.TextureLoader;
import common.Commons;
import common.LR;

public class EDFEnemyToki extends EDFEnemyBase {
	private static final int POWER = 1;
	private static final int HP = 10;
	private static final int SIZE = 40;
	private static final Texture TEXTURE = TextureLoader.loadTexture(Commons.IMAGE_FOLDER_STRING
			+ "dotTokiIcon.png");
	private static final float ROTATE_SPEED = 0.4f;
	private static final float FALL_SPEED = 0.3f;
	private static final int MONEY = 70;
	private static final EDFEnemyProperty property = new EDFEnemyProperty(
			POWER, HP, SIZE, TEXTURE, ROTATE_SPEED, FALL_SPEED, MONEY);

	public EDFEnemyToki(ShootingScene parentScene, float bornAngle, LR lr) {
		super(parentScene, bornAngle, lr, property);
	}

	@Override
	protected boolean updateProcess() {
		if (getOffsetY() < (WIDTH / 2) - SIZE) {
			setVOffsetY(getVOffsetY() * 0.95f);
		}
		// ランダムで射撃
		if ((FPSManager.totalFrame() % 60 == 0) && persentOf(15)) {
			((EDFScene) getParentScene()).add(new EDFNormalEnemyBullet(
					getParentScene(), this));
		}
		return super.updateProcess();
	}

	@Override
	protected Color createColor() {
		return new Color(random(0.4f, 0.6f), random(0.6f, 0.9f), random(0.5f,
				0.7f));
	}

}
