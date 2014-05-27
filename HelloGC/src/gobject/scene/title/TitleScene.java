package gobject.scene.title;

import static common.CommonMethod.*;
import static common.Commons.*;
import gobject.character.GameCharacterImpl;
import gobject.scene.GameSceneImpl;

import java.awt.Color;

import main.FPSManager;
import texture.TextTexture;

/**
 * 起動時に表示されるおなじみのシーン
 * 
 * @author shirakawa
 *
 */
public class TitleScene extends GameSceneImpl {
	public TitleScene() {
		BackGroundColor.WHITE.set();
		add(new TitleText());
	}

	private class TitleText extends GameCharacterImpl {
		public TitleText() {
			setX(WIDTH / 2);
			setY(HEIGHT / 2);
			setWidth(400);
			setHeight(50);
			setTexture(new TextTexture().createTextTexture("PRESS START",
					getWidth(), getHeight(), Color.blue));
		}

		@Override
		public void update() {
			if ((FPSManager.getCurrentFrame() + 1) % 30 == 0) {
				setY(getY() < 0 ? HEIGHT / 2 : -100);
			}
		}

		@Override
		public void render() {
			draw();
		}
	}

}
