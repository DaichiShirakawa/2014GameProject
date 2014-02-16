package gobject;

public interface GameObject{
	public abstract void dispose();
	public abstract void update();
	public void render();
	public boolean canDispose();
}

