package gobject.scene.shooting;

import gobject.character.GameCharacterImpl;

public abstract class GStgCharacter extends GameCharacterImpl {
    protected enum DIVISION {
        FRIENDLY,
        NEUTRAL,
        ENEMY
    }

    private DIVISION division_;

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {
        super.render();
    }

    public DIVISION getDivision() {
        return division_;
    }

    public void setDivision(DIVISION division) {
        this.division_ = division;
    }

    public void damage() {
        return;
    }
}
