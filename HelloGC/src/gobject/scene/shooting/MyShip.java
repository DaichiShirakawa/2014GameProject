package gobject.scene.shooting;

import static common.CommonMethod.*;
import static common.Commons.*;
import gobject.character.MoveMode;
import gobject.scene.shooting.bullet.NormalBullet;

import java.awt.Color;

import texture.TextureLoader;

public class MyShip extends GStgCharacter {
    private Color color;
    private float speed = 2.5f;
    private int size = 32;

    public MyShip() {
        setDivision(DIVISION.FRIENDLY);
        setTexture(new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
                + "tokiIcon.png"));
        setWidth(size);
        setHeight(size);
        setX(WIDTH / 2);
        setY(getHeight());
        color = new Color(0.6f, 0.6f, 1f);
        setXMoveMode(MoveMode.LOOP);
        setYMoveMode(MoveMode.LOOP);
    }

    @Override
    public void update() {
        setVy(0);
        setVx(0);
        if (KEYBOARD.isPress(KEY_UP)) {
            setVy(speed);
        }
        if (KEYBOARD.isPress(KEY_DOWN)) {
            setVy(-speed);
        }
        if (KEYBOARD.isPress(KEY_LEFT)) {
            setVx(-speed);
        }
        if (KEYBOARD.isPress(KEY_RIGHT)) {
            setVx(speed);
        }
        if (KEYBOARD.getPressLength(KEY_Z) % 5 == 0) {
            ShootingScene.getInstance().shoot(new NormalBullet(this));
        }
        super.update();
    }

    @Override
    public void render() {
        setGlColor4f(color, getAlpha());
        draw();
    }

}
