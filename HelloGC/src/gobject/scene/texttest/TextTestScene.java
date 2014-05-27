package gobject.scene.texttest;

import static common.CommonMethod.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.character.GameCharacterImpl;
import gobject.scene.GameSceneImpl;

import java.awt.Color;

import main.FPSManager;
import texture.TextTexture;

public class TextTestScene extends GameSceneImpl {

    public TextTestScene() {
        BackGroundColor.WHITE.set();
        add(new TestClass());
    }

    private class TestClass extends GameCharacterImpl {
        public TestClass() {
            setX(WIDTH / 2);
            setY(HEIGHT / 2);
            setWidth(400);
            setHeight(50);
            setTexture(new TextTexture().createTextTexture("aasd■てすとn", getWidth(),
                    getHeight(), Color.black));
        }

        @Override
        public void update() {
            getTexture().dispose();
            setTexture(new TextTexture().createTextTexture("ときtoki" + FPSManager.getCurrentFrame(),
                    getWidth(), getHeight(), Color.black));
        }

        @Override
        public void render() {
            glColor4f(0, 0, 0, 1);
            draw();
        }
    }

}
