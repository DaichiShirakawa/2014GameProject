package gobject;

public interface GameObject {
    void dispose();

    void update();

    void render();

    boolean canDispose();
}
