package gobject;

public interface GameObject{
	public void dispose();
	public void update();
	public void render();
	public boolean canDispose();
}

