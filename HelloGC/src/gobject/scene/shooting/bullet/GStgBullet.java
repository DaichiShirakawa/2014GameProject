package gobject.scene.shooting.bullet;

import gobject.scene.shooting.GStgCharacter;

public abstract class GStgBullet extends GStgCharacter {

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        return;
    }

    protected abstract void checkHit();

    protected abstract void hitEffect(GStgCharacter target);
}
