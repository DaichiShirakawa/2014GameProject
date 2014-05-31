package gobject.character.spaceship;

import static common.CommonMethod.*;
import static common.Commons.*;
import gobject.character.MoveMode;
import gobject.character.bullet.NormalBullet;
import gobject.scene.shooting.ShootingScene;

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
        setX(CENTER_X);
        setY(getHeight());
        color = new Color(0.6f, 0.6f, 1f);
        setMoveModeX(MoveMode.LOOP);
        setMoveModeY(MoveMode.LOOP);
    }

    @Override
    public void update() {
        setVy(0);
        setVx(0);
        if (KEYBOARD.isPressing(KEY.UP)) {
            setVy(speed);
        }
        if (KEYBOARD.isPressing(KEY.DOWN)) {
            setVy(-speed);
        }
        if (KEYBOARD.isPressing(KEY.LEFT)) {
            setVx(-speed);
        }
        if (KEYBOARD.isPressing(KEY.RIGHT)) {
            setVx(speed);
        }
        if (KEYBOARD.getPressingFrameCount(KEY.Z) % 5 == 0) {
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
