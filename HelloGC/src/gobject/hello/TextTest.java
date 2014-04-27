package gobject.hello;

import static common.CommonLogic.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.GameCharacter;
import gobject.GameScene;

import java.awt.Color;

import texture.TextTexture;

public class TextTest extends GameScene {

    public TextTest() {
        setClearColorWhite();
        addGO(new TestClass());
    }

    private class TestClass extends GameCharacter {
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
            setTexture(new TextTexture().createTextTexture("ときtoki" + getFrameCount(),
                    getWidth(), getHeight(), Color.black));
        }

        @Override
        public void render() {
            glColor4f(0, 0, 0, 1);
            draw();
        }
    }

}
