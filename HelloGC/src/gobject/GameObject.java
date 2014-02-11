package gobject;

public interface GameObject{
	public void terminate();
	public void update();
	public void render();
	public boolean canDispose();
}

