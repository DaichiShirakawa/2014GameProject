package gobject.character.shooting.test;

import static common.CommonMethod.*;
import static common.Commons.*;
import static java.lang.Math.*;
import gobject.character.MoveMode;
import gobject.character.shooting.ShootingCharacter;
import gobject.scene.shooting.ShootingScene;

import java.awt.Color;

import texture.TextureLoader;

public class EnemyShip extends ShootingCharacter {
    private int size = 32;
    private float thita = 0;
    private boolean damaging = false;
    private float dmgVibMove = 0;
    private float dmgVibThita = 0;

    public EnemyShip(ShootingScene scene) {
    	super(scene);
        setDivision(DIVISION.ENEMY);
        setTexture(new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
                + "DotTokiIcon.png"));
        setWidth(size);
        setHeight(size);
        setX(CENTER_X);
        setY(HEIGHT - getHeight());
        setColor(new Color(1f, 0.6f, 0.6f));
        setMoveModeX(MoveMode.LOOP);
        setMoveModeY(MoveMode.LOOP);
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
    public void takeDamage() {
        damaging = true;
        dmgVibMove = 5;
        dmgVibThita = 0;
        super.takeDamage();
    }

    public void damageUpdate() {
        if (!damaging) {
            return;
        }
        dmgVibMove *= 0.95f;
        dmgVibThita += 1;
        setX(getPixcelX() + dmgVibMove * (float) sin(dmgVibThita));
        if (dmgVibMove < 0.5) {
            damaging = false;
        }
    }
}
