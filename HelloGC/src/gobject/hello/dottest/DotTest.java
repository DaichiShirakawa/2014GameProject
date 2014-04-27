package gobject.hello.dottest;

import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.GameCharacter;

import org.lwjgl.input.Keyboard;

import texture.TextureLoader;

public class DotTest extends GameCharacter {

    public DotTest() {
        setTexture(new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
                + "dotTokiIcon.png"));
        setX(WIDTH / 2);
        setY(HEIGHT / 2);
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
