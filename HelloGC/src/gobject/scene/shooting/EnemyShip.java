package gobject.scene.shooting;

import static common.CommonMethod.*;
import static common.Commons.*;
import static java.lang.Math.*;
import gobject.character.MoveMode;

import java.awt.Color;

import texture.TextureLoader;

public class EnemyShip extends GStgCharacter {
    private int size = 32;
    //private int speed_ = 1;
    private float thita = 0;
    private boolean damaging = false;
    private float dmgVibMove = 0;
    private float dmgVibThita = 0;

    public EnemyShip() {
        setDivision(DIVISION.ENEMY);
        setTexture(new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
                + "DotTokiIcon.png"));
        setWidth(size);
        setHeight(size);
        setX(CENTER_X);
        setY(HEIGHT - getHeight());
        setColor(new Color(1f, 0.6f, 0.6f));
        //setVx(speed_);
        //setVy(speed_);
        setXMoveMode(MoveMode.LOOP);
        setYMoveMode(MoveMode.LOOP);
    }

    @Override
    public void update() {
        thita += 1 / (float) FPS;
        setX(CENTER_X + 100 * (float) sin(thita));
        setY(CENTER_Y + 100 * (float) cos(thita));
        damageUpdate();
        super.update();
    }

    @Override
    public void render() {
        setGlColor4f(getColor(), getAlpha());
        draw();
    }

    @Override
    public void damage() {
        damaging = true;
        dmgVibMove = 5;
        dmgVibThita = 0;
        super.damage();
    }

    public void damageUpdate() {
        if (!damaging) {
            return;
        }
        dmgVibMove *= 0.95f;
        dmgVibThita += 1;
        setX(getX() + dmgVibMove * (float) sin(dmgVibThita));
        if (dmgVibMove < 0.5) {
            damaging = false;
        }
    }
}
