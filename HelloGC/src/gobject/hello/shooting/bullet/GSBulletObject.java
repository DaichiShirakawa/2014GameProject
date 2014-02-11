package gobject.hello.shooting.bullet;

import gobject.hello.shooting.GShootingCharacterObect;

public abstract class GSBulletObject extends GShootingCharacterObect{
	public GSBulletObject() {
		setMoveMode(MOVEMODE_DISPOSE_WHEN_FADEOUT);
	}

	@Override
	public abstract void update();

	@Override
	public abstract void render();

	@Override
	public void terminate() {
		return;
	}
}
