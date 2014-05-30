package gobject.scene.dottest;

import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.character.GameCharacterImpl;
import gobject.scene.GameScene;
import gobject.scene.GameSceneImpl;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import texture.TextureLoader;

public class DotTestScene extends GameSceneImpl implements GameScene {

    public DotTestScene() {
        GL11.glClearColor(1f, 1f, 1f, 1f);
        add(new DotClass());
    }

    private class DotClass extends GameCharacterImpl {
        public DotClass() {
            setTexture(new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
                    + "dotTokiIcon.png"));
            setX(CENTER_X);
            setY(CENTER_Y);
            setWidth(getTexture().getWidth());
            setHeight(getTexture().getHeight());
        }

        @Override
        public void update() {

            while (Keyboard.next()) {
                if ((Keyboard.getEventKey() == Keyboard.KEY_UP)
                        && (Keyboard.getEventKeyState())) {
                    setScale(getScale() * 2);
                } else if ((Keyboard.getEventKey() == Keyboard.KEY_DOWN && (Keyboard
                        .getEventKeyState()))) {
                    setScale(getScale() / 2);
                } else if ((Keyboard.getEventKey() == Keyboard.KEY_LEFT)
                        && (Keyboard.getEventKeyState())) {
                    setAngle(getAngle() + 5f);
                } else if ((Keyboard.getEventKey() == Keyboard.KEY_RIGHT)
                        && (Keyboard.getEventKeyState())) {
                    setAngle(getAngle() - 5f);
                } else {
                    continue;
                }
                break;
            }

            setAngle(getAngle() + 1);
        }

        @Override
        public void render() {
            glColor4f(1, 0, 0, 1f);
            draw();
        }
    }
}
