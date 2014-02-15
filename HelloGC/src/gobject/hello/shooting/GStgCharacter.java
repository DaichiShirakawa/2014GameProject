package gobject.hello.shooting;

import gobject.GameCharacter;

public abstract class GStgCharacter extends GameCharacter {
	protected enum DIVISION{
	DIV_FRIENDLY,
	DIV_NEUTRAL,
	DIV_ENEMY}

	protected int division_;

	@Override
	public abstract void update();

	@Override
	public abstract void render();

}
