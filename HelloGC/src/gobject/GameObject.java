package gobject;

public interface GameObject {

    void update();

    void render();

    void dispose();

    boolean canDispose();
}
