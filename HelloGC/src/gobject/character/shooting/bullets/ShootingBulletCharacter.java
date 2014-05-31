package gobject.character.shooting.bullets;

import gobject.character.shooting.ShootingCharacter;
import gobject.scene.shooting.ShootingScene;

public abstract class ShootingBulletCharacter extends ShootingCharacter {
    public ShootingBulletCharacter(ShootingScene scene) {
		super(scene);
	}

	@Override
    public void dispose() {
        return;
    }

    protected abstract void checkHit();

    protected abstract void hitEffect(ShootingCharacter target);
}
