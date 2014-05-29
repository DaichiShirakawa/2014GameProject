package gobject.scene.title;

import static common.CommonMethod.*;
import static common.Commons.*;
import gobject.character.GameCharacterImpl;
import gobject.scene.GameScene;
import gobject.scene.GameSceneImpl;

import java.awt.Color;

import main.FPSManager;
import texture.TextTextureMaker;

/**
 * 起動時に表示されるおなじみの画面
 * 
 * @author shirakawa
 * 
 */
public class TitleScene extends GameSceneImpl implements GameScene {
	public TitleScene() {
		BackGroundColor.WHITE.set();
		add(new TitleText());
	}

	private class TitleText extends GameCharacterImpl {
		public TitleText() {
			setX(WIDTH / 2);
			setY(HEIGHT / 2);
			setWidth(200);
			setHeight(50);
			setTexture(TextTextureMaker.createTextTexture("PRESS START",
					400, (int)TRUE_TYPE_FONT_HEIGHT, Color.blue));
		}

		@Override
		public void update() {
			if ((FPSManager.getFramesUntilStart() + 1) % 30 == 0) {
				setY(getY() < 0 ? HEIGHT / 2 : -100);
			}
		}

		@Override
		public void render() {
			draw();
		}
	}

}
