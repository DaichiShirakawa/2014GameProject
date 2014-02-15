package gobject.hello.shooting.bullet;

import gobject.hello.shooting.GStgCharacter;

public abstract class GStgBullet extends GStgCharacter{
	public GStgBullet() {
		setMoveMode(MOVEMODE_DISPOSE_WHEN_FADEOUT);
	}

	@Override
	public abstract void update();

	@Override
	public abstract void render();

	@Override
	public void dispose() {
		return;
	}
}
