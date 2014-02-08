package gobject;

public abstract class GLogicObject implements GameObject {

	@Override
	public abstract void terminate();

	@Override
	public abstract void update();

	@Override
	public void render() {
		return;
	}

}
