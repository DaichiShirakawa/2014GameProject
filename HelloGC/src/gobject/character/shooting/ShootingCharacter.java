package gobject.character.shooting;

import gobject.character.GameCharacterImpl;
import gobject.character.shooting.bullets.ShootingBulletCharacter;
import gobject.scene.shooting.ShootingScene;

public abstract class ShootingCharacter extends GameCharacterImpl {
	private ShootingScene scene;
	private DIVISION division;

	protected enum DIVISION {
		FRIENDLY,
		NEUTRAL,
		ENEMY
	}

	public ShootingCharacter(ShootingScene scene) {
		this.scene = scene;
	}

	public DIVISION getDivision() {
		return division;
	}

	public void setDivision(DIVISION division) {
		this.division = division;
	}

	public void takeDamage() {
		// 必要に応じてオーバーライド
	}
	
	public void shoot(ShootingBulletCharacter bullet) {
		scene.shoot(this, bullet);
	}
}
