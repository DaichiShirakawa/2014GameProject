package classes.character;

import scenes.shootingtest.ShootingBulletCharacterImpl;
import classes.scene.ShootingScene;

public abstract class ShootingCharacter extends GameCharacterImpl {
	private ShootingScene parentScene;
	private DIVISION division;

	public enum DIVISION {
		FRIENDLY,
		NEUTRAL,
		ENEMY
	}

	public ShootingCharacter(ShootingScene scene) {
		this.parentScene = scene;
	}

	public DIVISION getDivision() {
		return division;
	}

	public void setDivision(DIVISION division) {
		this.division = division;
	}

	public void takeDamage(float damage) {
		// 被弾リアクション用。必要に応じてオーバーライド
	}

	protected void setParentScene(ShootingScene parentScene) {
		this.parentScene = parentScene;
	}

	public ShootingScene getParentScene() {
		return parentScene;
	}

	public void shoot(ShootingBulletCharacterImpl bullet) {
		bullet.setParentScene(parentScene);
		parentScene.shoot(bullet);
	}
}
