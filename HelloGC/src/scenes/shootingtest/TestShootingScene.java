package scenes.shootingtest;

import java.awt.Color;

import classes.character.GameCharacterBasePoint;
import classes.character.TextCharacter;
import classes.scene.ShootingScene;
import common.CommonMethod.BackGroundColor;

public final class TestShootingScene extends ShootingScene {

	public TestShootingScene() {
		BackGroundColor.BLACK.set();
		add(new TestMyShip(this));
		add(new TestEnemyShip(this));

		add(new TextCharacter("input: ↑,↓,→,←,SPACE")).setScale(0.3f)
				.setBasePont(GameCharacterBasePoint.LEFTBOTTOM)
				.setX(5)
				.setY(5)
				.setColor(Color.white);
	}
}
