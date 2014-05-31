package gobject.scene.shooting;

import gobject.character.shooting.ShootingCharacter;
import gobject.character.shooting.bullets.ShootingBulletCharacter;

public interface ShootingScene {
	void shoot(ShootingCharacter shooter, ShootingBulletCharacter bullet);
}
