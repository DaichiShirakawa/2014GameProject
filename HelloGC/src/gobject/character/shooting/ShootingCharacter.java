package gobject.character.shooting;

import gobject.character.GameCharacterImpl;
import gobject.character.shooting.bullets.ShootingBulletCharacter;
import gobject.scene.shooting.ShootingScene;

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

	public void takeDamage() {
		// 被弾リアクション用。必要に応じてオーバーライド
	}

	protected void setParentScene(ShootingScene parentScene) {
		this.parentScene = parentScene;
	}

	public ShootingScene getParentScene() {
		return parentScene;
	}

	public void shoot(ShootingBulletCharacter bullet) {
		bullet.setParentScene(parentScene);
		parentScene.shoot(this, bullet);
	}
}
