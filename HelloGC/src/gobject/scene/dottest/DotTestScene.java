package gobject.scene.dottest;

import static common.Commons.*;
import gobject.character.GameCharacterImpl;
import gobject.scene.GameSceneImpl;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import texture.TextureLoader;

import common.Commons.KEY;

public class DotTestScene extends GameSceneImpl {

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
			setColor(Color.red);
		}

		@Override
		protected void processInput() {
			if (KEYBOARD.isPressed(KEY.UP)) {
				setScale(getScale() * 2);
			}
			if (KEYBOARD.isPressed(KEY.DOWN)) {
				setScale(getScale() / 2);
			}
			if (KEYBOARD.isPressed(KEY.LEFT)) {
				setAngle(getAngle() + 5f);
			}
			if (KEYBOARD.isPressed(KEY.RIGHT)) {
				setAngle(getAngle() - 5f);
			}
		}
	}
}
