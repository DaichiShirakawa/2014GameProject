package gobject.scene.title;

import static common.Commons.*;
import gobject.character.GameCharacter;
import gobject.character.GameCharacterImpl;
import gobject.scene.GameScene;
import gobject.scene.GameSceneImpl;

import java.awt.Color;

import texture.text.TextTextureMaker;

import common.CommonMethod.BackGroundColor;


/**
 * 起動時に表示されるおなじみの画面
 * 
 * PRESS START!
 * 
 * @author shirakawa
 * 
 */
public class TitleScene extends GameSceneImpl implements GameScene {
	public TitleScene() {
		BackGroundColor.WHITE.set();
		add(new TitleText());
	}

	private class TitleText extends GameCharacterImpl implements GameCharacter {
		public TitleText() {
			setX(CENTER_X);
			setY(CENTER_Y);
			setTexture(TextTextureMaker.createText("PRESS START!", Color.blue));
			setWidth(getTexture().getWidth());
			setHeight(getTexture().getHeight());
		}

		@Override
		public void update() {
//			if (FPSManager.getFramesUntilStart() % 45 == 0) {
//				if (isVisible()) {
//					hide();
//				} else {
//					show();
//				}
//			}
		}
	}

}
