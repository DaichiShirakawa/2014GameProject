package glogic;

import static common.CommonLogic.*;
import static common.Commons.*;
import gobject.GameCharacter;
import gobject.GameScene;

import java.awt.Color;

import texture.TextTexture;

public class TitleScene extends GameScene {
    public TitleScene() {
        setClearColorWhite();
        addGO(new TestClass());
    }

    private class TestClass extends GameCharacter {
        public TestClass() {
            setX(WIDTH / 2);
            setY(HEIGHT / 2);
            setWidth(400);
            setHeight(50);
            setTexture(new TextTexture().createTextTexture("PRESS START", getWidth(),
                    getHeight(), Color.blue));
        }

        @Override
        public void update() {
            if (getFrameCount() % 30 == 0) {
                setY(getY() < 0 ? HEIGHT / 2 : -100);
            }
        }

        @Override
        public void render() {
            draw();
        }
    }

}
